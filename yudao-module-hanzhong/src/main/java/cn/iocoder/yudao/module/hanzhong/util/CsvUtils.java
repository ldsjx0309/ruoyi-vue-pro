package cn.iocoder.yudao.module.hanzhong.util;

/**
 * CSV 导出工具类
 *
 * @author hanzhong
 */
public final class CsvUtils {

    private CsvUtils() {
    }

    /**
     * 将值转为 CSV 字符串（null 转空字符串）
     */
    public static String str(Object val) {
        return val == null ? "" : val.toString();
    }

    /**
     * 对 CSV 单元格内容进行转义：
     * 1. 防止 CSV 注入（Formula Injection）：以 =、+、-、@、TAB、CR 开头的值前置单引号
     * 2. 包含逗号、双引号或换行时用双引号包裹，内部双引号替换为两个双引号
     */
    public static String escapeCsv(String val) {
        if (val == null) {
            return "";
        }
        // Prevent formula/macro injection (Excel, LibreOffice)
        if (!val.isEmpty()) {
            char first = val.charAt(0);
            if (first == '=' || first == '+' || first == '-' || first == '@'
                    || first == '\t' || first == '\r') {
                val = "'" + val;
            }
        }
        if (val.contains(",") || val.contains("\"") || val.contains("\n")) {
            return "\"" + val.replace("\"", "\"\"") + "\"";
        }
        return val;
    }

}
