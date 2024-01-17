package com.sorrowphage.czp.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author: SorrowPhage
 * @date: 2023/12/21
 */
public class CollectionUtils {

    /**
     * 将一个List拆分成固定长度的多个List集合
     *
     * @param dataList  List数据源
     * @param splitSize 拆分的长度
     * @param <T>       泛型
     * @return 多个List集合
     */
    public static <T> List<List<T>> splitList(List<T> dataList, int splitSize) {
        List<List<T>> splitList = new ArrayList<>();
        if (dataList == null || dataList.size() == 0) {
            return splitList;
        }
        int splitListSize = dataList.size() % splitSize == 0 ? dataList.size() / splitSize : dataList.size() / splitSize + 1;
        for (int i = 0; i < splitListSize; i++) {
            if (splitListSize == i + 1) {
                splitList.add(dataList.subList(i * splitSize, dataList.size()));
            } else {
                splitList.add(dataList.subList(i * splitSize, (i + 1) * splitSize));
            }
        }
        return splitList;
    }

    /**
     * 获取List中指定属性重复的对象列表
     *
     * @param dataList      数据列表
     * @param keyExtractors 指定的属性
     * @param <T>
     * @return
     */
    @SafeVarargs
    public static <T> List<T> repeatFilter(List<T> dataList, Function<? super T, ?>... keyExtractors) {
        Function<T, List<?>> tListFunction = combinationKey(keyExtractors);
        System.out.println(tListFunction);
        return dataList.stream()
                .collect(Collectors.groupingBy(combinationKey(keyExtractors)))
                .values()
                .stream()
                .filter(list -> list.size() > 1)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @SafeVarargs
    public static <T> Function<T, List<?>> combinationKey(Function<? super T, ?>... keyExtractors) {
        return new Function<T, List<?>>() {
            @Override
            public List<?> apply(T t) {
                return Arrays.stream(keyExtractors).map(key -> key.apply(t)).collect(Collectors.toList());
            }
        };
    }



}
