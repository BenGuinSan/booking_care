package com.penguinsan.BookingCare.utils;

import lombok.experimental.UtilityClass;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@UtilityClass
public class FileUploadUtils {
    public static final long MAX_FILE_SIZE = 5 * 1024 * 1024;

    public static final String IMAGE_PATTERN = "([^\\s]+(\\.(?i)(jpg|png|gif|bmp))$)";

    public static final String DATE_FORMAT = "yyyyMMddHHmmss";

    public static final String FILE_NAME_FORMAT = "%s_%s_%s";

    public static boolean isAllowedExtension(final String fileName, final String pattern) {
        final Matcher matcher = Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(fileName);
        return matcher.matches();
    }

    public static void assertAllowed(MultipartFile file, final String pattern){
        final long size = file.getSize();

        if(size > MAX_FILE_SIZE){
            throw new FuncErrorException("Max file size is 2MB");
        }

        final String fileName = file.getOriginalFilename();
        final String extension = FilenameUtils.getExtension(fileName);

        if(!isAllowedExtension(fileName, pattern)){
            throw new IllegalArgumentException("Only jpg, png, gif, bmp files");
        }
    }

    public static String getFileName(final String name){
        final DateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
        final String date = dateFormat.format(System.currentTimeMillis());
        return String.format(FILE_NAME_FORMAT, name, date);
    }

    public class FuncErrorException extends RuntimeException {
        private static final long serialVersionUID = 5003320033602480096L;
        public FuncErrorException(final String message) {
            super(message);
        }
    }
}
