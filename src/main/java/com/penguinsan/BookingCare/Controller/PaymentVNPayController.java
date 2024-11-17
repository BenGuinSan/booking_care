package com.penguinsan.BookingCare.Controller;

import com.penguinsan.BookingCare.Config.VnPayConfig;
import com.penguinsan.BookingCare.DTO.PaymentRequest;
import com.penguinsan.BookingCare.DTO.VnPayRepesDTO;
import com.penguinsan.BookingCare.Model.Appointment;
import com.penguinsan.BookingCare.Model.Payments;
import com.penguinsan.BookingCare.Repository.PaymentRepository;
import com.penguinsan.BookingCare.Service.AppointmentService;
import com.penguinsan.BookingCare.Service.PaymentService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

import java.util.*;

import static com.penguinsan.BookingCare.Config.VnPayConfig.*;


@RestController
@RequestMapping("paymentvnpay")
public class PaymentVNPayController {

    @Autowired
    PaymentService paymentService;
    PaymentRepository paymentRepository;
    AppointmentService appointmentService;

    @Autowired
    public PaymentVNPayController(PaymentService paymentService, PaymentRepository paymentRepository, AppointmentService appointmentService) {
        this.paymentService = paymentService;
        this.paymentRepository = paymentRepository;
        this.appointmentService = appointmentService;
    }


    @PostMapping("/vnpay/{appointmentId}")
    public ResponseEntity<?> createVnPayPayment(HttpServletRequest req, @PathVariable int appointmentId) throws UnsupportedEncodingException {

        Appointment appointment = new Appointment();
        appointment = appointmentService.getAppointmentById(appointmentId);
        int paymentId = appointment.getPayment().getPayment_Id();
        Payments payment = paymentService.getPaymentById(paymentId);

        long amount = payment.getAmount()*100L;
        System.out.println("Amount: " + amount);
        String bankCode = "NCB";

        String vnp_TxnRef = VnPayConfig.getRandomNumber(8);
        String vnp_IpAddr = VnPayConfig.getIpAddress(req);

        String vnp_TmnCode = VnPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        vnp_Params.put("vnp_Amount", String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode", "VND");
        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);
        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VnPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VnPayConfig.hmacSHA512(VnPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VnPayConfig.vnp_PayUrl + "?" + queryUrl;
        VnPayRepesDTO vnPayRepesDTO = new VnPayRepesDTO();
        vnPayRepesDTO.setStatus("00");
        vnPayRepesDTO.setMessage("success");
        vnPayRepesDTO.setURL(paymentUrl);
        return ResponseEntity.ok(vnPayRepesDTO);

    }


    @GetMapping("/callback/{appointmentId}")
    public ResponseEntity<String> handleVNPayCallback(@PathVariable int appointmentId) {
        System.out.println("tesssssssssssssss");
        Appointment appointment = new Appointment();
        appointment = appointmentService.getAppointmentById(appointmentId);
        int paymentId = appointment.getPayment().getPayment_Id();
        Payments payment = paymentService.getPaymentById(paymentId);
        if (payment != null) {
            payment.setPayment_method(Payments.Payment_method.ONLINE);
            payment.setPayment_Date(new Date());
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Payment not found.");
        }
        paymentRepository.save(payment);

        return ResponseEntity.ok("Payment recorded successfully.");

    }
    private String generateHashData(Map<String, String> params) {
        // Tạo chuỗi hash data từ các tham số theo thứ tự abc (trừ `vnp_SecureHash`)
        StringBuilder hashData = new StringBuilder();
        params.entrySet().stream()
                .filter(entry -> !entry.getKey().equals("vnp_SecureHash"))
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> hashData.append(entry.getKey())
                        .append("=")
                        .append(entry.getValue())
                        .append("&"));
        // Xóa ký tự `&` cuối cùng
        hashData.deleteCharAt(hashData.length() - 1);
        return hashData.toString();
    }

    private String hmacSHA512(String key, String data) {
        try {
            Mac hmac = Mac.getInstance("HmacSHA512");
            SecretKeySpec secretKeySpec = new SecretKeySpec(key.getBytes(), "HmacSHA512");
            hmac.init(secretKeySpec);
            byte[] hashBytes = hmac.doFinal(data.getBytes());
            return bytesToHex(hashBytes);
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate HMAC SHA-512", e);
        }
    }

    private String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }



}