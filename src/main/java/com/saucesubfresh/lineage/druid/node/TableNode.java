package com.saucesubfresh.lineage.druid.node;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>
 * 数据血缘解析时表节点
 * </p>
 *
 * @author <a href="https://github.com/Code-13/">code13</a>
 * @since 2023/8/3 21:09
 */
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TableNode {

    /**
     * schema
     */
    private String schemaName;
    /**
     * 表名
     */
    private String name;
    /**
     * 别名
     */
    private String alias;
    /**
     * 是否为虚拟表
     */
    @Builder.Default
    private Boolean isVirtualTemp = false;

    /**
     * 特殊类型节点的处理
     */
    private String queryType;
    /**
     * 字段列表
     */
    private final List<ColumnNode> columns = new ArrayList<>();
    /**
     * 表达式
     */
    private String expression;
}
