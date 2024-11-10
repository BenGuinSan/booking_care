package com.penguinsan.BookingCare.utils;

import jakarta.servlet.http.HttpServletRequest;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class VNPayUtil {

    // Hàm HMAC SHA512 để tạo chữ ký
    public static String hmacSHA512(final String key, final String data) {
        try {
            if (key == null || data == null) {
                throw new IllegalArgumentException("Key hoặc data không được null.");
            }
            final Mac hmac512 = Mac.getInstance("HmacSHA512");
            byte[] hmacKeyBytes = key.getBytes(StandardCharsets.UTF_8);
            final SecretKeySpec secretKeySpec = new SecretKeySpec(hmacKeyBytes, "HmacSHA512");
            hmac512.init(secretKeySpec);
            byte[] dataBytes = data.getBytes(StandardCharsets.UTF_8);
            byte[] result = hmac512.doFinal(dataBytes);
            StringBuilder sb = new StringBuilder(2 * result.length);
            for (byte b : result) {
                sb.append(String.format("%02x", b & 0xff));
            }
            return sb.toString();
        } catch (Exception ex) {
            return "Error in HMAC SHA512: " + ex.getMessage();
        }
    }

    // Hàm tạo chữ ký vnp_SecureHash từ các tham số và khóa bí mật
    public static String createSecureHash(Map<String, String> params, String secretKey) {
        StringBuilder hashData = new StringBuilder();
        params.entrySet().stream()
                .filter(entry -> entry.getValue() != null && !entry.getKey().equals("vnp_SecureHash"))
                .sorted(Map.Entry.comparingByKey())
                .forEach(entry -> {
                    if (hashData.length() > 0) {
                        hashData.append("&");
                    }
                    hashData.append(entry.getKey()).append("=").append(entry.getValue());
                });

        return hmacSHA512(secretKey, hashData.toString());
    }

    // Hàm tạo URL thanh toán với các tham số mã hóa
    public static String getPaymentURL(Map<String, String> paramsMap, String secretKey, boolean encodeKey) {
        String paymentURL = paramsMap.entrySet().stream()
                .filter(entry -> entry.getValue() != null && !entry.getValue().isEmpty())
                .sorted(Map.Entry.comparingByKey())
                .map(entry ->
                        (encodeKey ? URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8)
                                : entry.getKey()) + "=" +
                                URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));

        // Thêm vnp_SecureHash vào cuối URL
        String vnpSecureHash = createSecureHash(paramsMap, secretKey);
        return paymentURL + "&vnp_SecureHash=" + vnpSecureHash;
    }

    // Hàm lấy địa chỉ IP của request
    public static String getIpAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.isEmpty()) {
            ipAddress = request.getRemoteAddr();
        }
        return ipAddress != null ? ipAddress : "Không xác định";
    }

    // Hàm tạo số ngẫu nhiên
    public static String getRandomNumber(int len) {
        Random rnd = new Random();
        String chars = "0123456789";
        StringBuilder sb = new StringBuilder(len);
        for (int i = 0; i < len; i++) {
            sb.append(chars.charAt(rnd.nextInt(chars.length())));
        }
        return sb.toString();
    }
}
