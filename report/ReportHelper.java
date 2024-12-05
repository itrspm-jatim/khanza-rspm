public class ReportHelper {
    public static String formatAturanPakai(String aturanPakai) {
        String baris1 = aturanPakai.split("TABLET|TETES|SENDOK|KAPSUL")[0] + "TABLET";
        String baris2 = "";
        String baris3 = "";

        if (aturanPakai.contains("PAGI")) baris2 += "PAGI (07.00) ";
        if (aturanPakai.contains("SIANG")) baris2 += "SIANG (13.00) ";
        if (aturanPakai.contains("SORE")) baris2 += "SORE (19.00) ";
        if (aturanPakai.contains("MALAM")) baris2 += "MALAM (21.00) ";

        baris3 = aturanPakai.replaceFirst(".*(PAGI|SIANG|SORE|MALAM)", "").trim();

        return baris1 + "\n" + baris2.trim() + "\n" + baris3;
    }
}
