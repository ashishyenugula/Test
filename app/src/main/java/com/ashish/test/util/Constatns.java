package com.ashish.test.util;



public final class Constatns {
    public static final class Pixabay {
        public static final String PIXABAY_BASE_URL = "https://pixabay.com/api/";
        private static final String PIXABAY_API_KEY = "17342253-cde056ca67a2868f81d4599cb";
        private static final int RESULTS_PER_PAGE = 200;

        public static final String PIXABAY_API_PATH = "?image_type=photo"
                + "&key=" + PIXABAY_API_KEY
                + "&per_page=" + RESULTS_PER_PAGE;
    }
}
