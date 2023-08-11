package com.saucesubfresh.lineage.druid.process.sqlexpr;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.dialect.postgresql.ast.expr.PGTypeCastExpr;
import com.saucesubfresh.lineage.druid.process.ProcessorRegister;
import com.saucesubfresh.lineage.druid.process.SqlObjectType;

/**
 * <p>
 * PGTypeCastExpr
 * Use case:
 * CASE WHEN condition THEN result
 * [WHEN ...]
 * [ELSE result]
 * END
 * </p>
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:15
 */
@SqlObjectType(clazz = PGTypeCastExpr.class)
public class PGTypeCastExprProcessor implements SQLExprProcessor {

    @Override
    public void process(String dbType, SQLExpr expr, SqlExprContent content) {
        PGTypeCastExpr pgTypeCastExpr = (PGTypeCastExpr) expr;
        SQLExpr pgTypeCastExprExpr = pgTypeCastExpr.getExpr();
        ProcessorRegister.getSQLExprProcessor(pgTypeCastExprExpr.getClass())
                .process(dbType, pgTypeCastExprExpr, content);
    }
}
