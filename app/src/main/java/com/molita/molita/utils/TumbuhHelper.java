package com.molita.molita.utils;

public class TumbuhHelper {

    public static String getGrowthDescription(int age, double weight, double height) {
        // Rentang berat badan normal (kg) berdasarkan umur (dalam tahun)
        double[][] weightRanges = {
                {2.5, 4.0},  // 0 tahun
                {8.0, 12.5}, // 1 tahun
                {9.5, 14.5}, // 2 tahun
                {11.5, 18.0}, // 3 tahun
                {13.5, 22.0} // 4 tahun
        };

        // Rentang tinggi badan normal (cm) berdasarkan umur (dalam tahun)
        double[][] heightRanges = {
                {45.0, 55.0}, // 0 tahun
                {70.0, 82.0}, // 1 tahun
                {80.0, 92.0}, // 2 tahun
                {85.0, 105.0}, // 3 tahun
                {90.0, 115.0} // 4 tahun
        };

        // Validasi umur dalam rentang data
        if (age < 0 || age >= weightRanges.length) {
            return "Umur di luar rentang data. Silakan masukkan umur antara 0 hingga " + (weightRanges.length - 1) + " tahun.";
        }

        StringBuilder description = new StringBuilder("Evaluasi tumbuh kembang anak:\n");

        // Evaluasi berat badan
        if (weight < weightRanges[age][0]) {
            description.append("- Berat badan terlalu rendah untuk usia ").append(age).append(" tahun.\n")
                    .append("  Disarankan: Konsultasikan dengan dokter anak untuk memastikan kebutuhan nutrisi terpenuhi.\n");
        } else if (weight > weightRanges[age][1]) {
            description.append("- Berat badan melebihi batas normal untuk usia ").append(age).append(" tahun.\n")
                    .append("  Disarankan: Evaluasi pola makan dan aktivitas fisik anak.\n");
        } else {
            description.append("- Berat badan berada dalam rentang normal untuk usia ").append(age).append(" tahun.\n")
                    .append("  Pertahankan pola makan seimbang dan aktivitas fisik teratur.\n");
        }

        // Evaluasi tinggi badan
        if (height < heightRanges[age][0]) {
            description.append("- Tinggi badan terlalu rendah untuk usia ").append(age).append(" tahun.\n")
                    .append("  Disarankan: Konsultasikan dengan dokter anak untuk memastikan tidak ada masalah pertumbuhan.\n");
        } else if (height > heightRanges[age][1]) {
            description.append("- Tinggi badan di atas batas normal untuk usia ").append(age).append(" tahun.\n")
                    .append("  Ini bisa jadi indikasi pertumbuhan pesat. Tetap pantau perkembangan anak secara berkala.\n");
        } else {
            description.append("- Tinggi badan berada dalam rentang normal untuk usia ").append(age).append(" tahun.\n")
                    .append("  Pertahankan asupan nutrisi dan kebiasaan hidup sehat.\n");
        }

        return description.toString();
    }

    // Fungsi untuk mengevaluasi berat badan
    public static String evaluateWeight(int age, double weight) {
        // Rentang berat badan normal (kg) berdasarkan umur (dalam tahun)
        double[][] weightRanges = {
                {2.5, 4.0},  // 0 tahun
                {8.0, 12.5}, // 1 tahun
                {9.5, 14.5}, // 2 tahun
                {11.5, 18.0}, // 3 tahun
                {13.5, 22.0} // 4 tahun
        };

        // Validasi umur
        if (age < 0 || age >= weightRanges.length) {
            return "Umur di luar rentang data. Silakan masukkan umur antara 0 hingga " + (weightRanges.length - 1) + " tahun.";
        }

        // Evaluasi berat badan
        if (weight < weightRanges[age][0]) {
            return "Berat badan terlalu rendah untuk usia " + age + " tahun. Disarankan: Konsultasikan dengan dokter anak untuk memastikan kebutuhan nutrisi terpenuhi.";
        } else if (weight > weightRanges[age][1]) {
            return "Berat badan melebihi batas normal untuk usia " + age + " tahun. Disarankan: Evaluasi pola makan dan aktivitas fisik anak.";
        } else {
            return "Berat badan berada dalam rentang normal untuk usia " + age + " tahun. Pertahankan pola makan seimbang dan aktivitas fisik teratur.";
        }
    }

    // Fungsi untuk mengevaluasi tinggi badan
    public static String evaluateHeight(int age, double height) {
        // Rentang tinggi badan normal (cm) berdasarkan umur (dalam tahun)
        double[][] heightRanges = {
                {45.0, 55.0}, // 0 tahun
                {70.0, 82.0}, // 1 tahun
                {80.0, 92.0}, // 2 tahun
                {85.0, 105.0}, // 3 tahun
                {90.0, 115.0} // 4 tahun
        };

        // Validasi umur
        if (age < 0 || age >= heightRanges.length) {
            return "Umur di luar rentang data. Silakan masukkan umur antara 0 hingga " + (heightRanges.length - 1) + " tahun.";
        }

        // Evaluasi tinggi badan
        if (height < heightRanges[age][0]) {
            return "Tinggi badan terlalu rendah untuk usia " + age + " tahun. Disarankan: Konsultasikan dengan dokter anak untuk memastikan tidak ada masalah pertumbuhan.";
        } else if (height > heightRanges[age][1]) {
            return "Tinggi badan di atas batas normal untuk usia " + age + " tahun. Ini bisa jadi indikasi pertumbuhan pesat. Tetap pantau perkembangan anak secara berkala.";
        } else {
            return "Tinggi badan berada dalam rentang normal untuk usia " + age + " tahun. Pertahankan asupan nutrisi dan kebiasaan hidup sehat.";
        }
    }

}
