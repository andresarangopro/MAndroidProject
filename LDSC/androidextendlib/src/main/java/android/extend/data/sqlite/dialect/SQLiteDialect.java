package android.extend.data.sqlite.dialect;


import android.extend.data.sqlite.meta.Sql;

/**
 * Created by tanqimin on 2015/11/3.
 */
public class SQLiteDialect
        extends DefaultDialect {
    @Override
    public String getDialectName() {
        return "sqlite";
    }

    @Override
    public Sql selectTop(
            int currentPage,
            int recordsPerPage,
            String sql,
            Object[] params) {
        if (currentPage == 1 && recordsPerPage == 1) {
            //如果sql本身只返回一个结果
            if (selectSinglePattern.matcher(sql).find()) {
                return new Sql(sql);
            }
        }
        int offset = recordsPerPage * (currentPage - 1);
        return new Sql(sql, params).append("LIMIT ? OFFSET ?", recordsPerPage, offset);
    }
}
