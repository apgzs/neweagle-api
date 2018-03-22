package com.neweagle.api.comm.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Collections工具集.
 * 在JDK的Collections和Guava的Collections2后, 命名为Collections3.
 *
 * @author zhangxd
 */
@SuppressWarnings("rawtypes")
public final class Collections3 {

    private Collections3() {
        throw new IllegalAccessError("Utility class");
    }


    /**
     * 转换Collection所有元素(通过toString())为String, 中间以 separator分隔。
     *
     * @param collection the collection
     * @param separator  the separator
     * @return the string
     */
    public static String convertToString(final Collection collection, final String separator) {
        return StringUtils.join(collection, separator);
    }

    /**
     * 转换Collection所有元素(通过toString())为String, 每个元素的前面加入prefix，后面加入postfix，如<div>message</div>。
     *
     * @param collection the collection
     * @param prefix     the prefix
     * @param postfix    the postfix
     * @return the string
     */
    public static String convertToString(final Collection collection, final String prefix, final String postfix) {
        StringBuilder builder = new StringBuilder();
        for (Object o : collection) {
            builder.append(prefix).append(o).append(postfix);
        }
        return builder.toString();
    }

    /**
     * 判断是否为空.
     *
     * @param collection the collection
     * @return the boolean
     */
    public static boolean isEmpty(Collection collection) {
        return collection == null || collection.isEmpty();
    }

    /**
     * 取得Collection的第一个元素，如果collection为空返回null.
     *
     * @param <T>        the type parameter
     * @param collection the collection
     * @return the first
     */
    public static <T> T getFirst(Collection<T> collection) {
        if (isEmpty(collection)) {
            return null;
        }

        return collection.iterator().next();
    }

    /**
     * 获取Collection的最后一个元素 ，如果collection为空返回null.
     *
     * @param <T>        the type parameter
     * @param collection the collection
     * @return the last
     */
    public static <T> T getLast(Collection<T> collection) {
        if (isEmpty(collection)) {
            return null;
        }

        //当类型为List时，直接取得最后一个元素 。
        if (collection instanceof List) {
            List<T> list = (List<T>) collection;
            return list.get(list.size() - 1);
        }

        //其他类型通过iterator滚动到最后一个元素.
        Iterator<T> iterator = collection.iterator();
        while (true) {
            T current = iterator.next();
            if (!iterator.hasNext()) {
                return current;
            }
        }
    }

    /**
     * 返回a+b的新List.
     *
     * @param <T> the type parameter
     * @param a   the a
     * @param b   the b
     * @return the list
     */
    public static <T> List<T> union(final Collection<T> a, final Collection<T> b) {
        List<T> result = new ArrayList<>(a);
        result.addAll(b);
        return result;
    }

    /**
     * 返回a-b的新List.
     *
     * @param <T> the type parameter
     * @param a   the a
     * @param b   the b
     * @return the list
     */
    public static <T> List<T> subtract(final Collection<T> a, final Collection<T> b) {
        List<T> list = new ArrayList<>(a);
        b.forEach(list::remove);

        return list;
    }

    /**
     * 返回a与b的交集的新List.
     *
     * @param <T> the type parameter
     * @param a   the a
     * @param b   the b
     * @return the list
     */
    public static <T> List<T> intersection(Collection<T> a, Collection<T> b) {

        return a.stream().filter(b::contains).collect(Collectors.toList());
    }
}
