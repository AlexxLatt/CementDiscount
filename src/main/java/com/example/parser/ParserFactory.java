package com.example.parser;

import com.example.adapters.DataParserAdapter;
import org.apache.commons.io.FilenameUtils;

public class ParserFactory {

    public enum TypeParser {
        TXT("txt"),
        NOTYPE("");


        private final String extension;

        TypeParser(String extension) {
            this.extension = extension;
        }

        public String getExtension() {
            return extension;
        }

        public static TypeParser fromString(String ext) {
            for (TypeParser type : TypeParser.values()) {
                if (type.extension.equalsIgnoreCase(ext)) {
                    return type;
                }
            }
            throw new IllegalArgumentException("Неподдерживаемый тип: " + ext);
        }
    }

    public static DataParser create(String filePath) {

        String extension = FilenameUtils.getExtension(filePath);
        ParserFactory.TypeParser type = ParserFactory.TypeParser.fromString(extension);

        return switch (type) {
            case TXT -> new DataParserImpl();
            case NOTYPE -> new DataParserAdapter(new DataParserImpl());

        };

    }
}
