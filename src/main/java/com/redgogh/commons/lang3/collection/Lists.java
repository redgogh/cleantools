package com.redgogh.commons.lang3.collection;

/* -------------------------------------------------------------------------------- *\
|*                                                                                  *|
|*    Copyright (C) 2019-2024 RedGogh All rights reserved.                          *|
|*                                                                                  *|
|*    Licensed under the Apache License, Version 2.0 (the "License");               *|
|*    you may not use this file except in compliance with the License.              *|
|*    You may obtain a copy of the License at                                       *|
|*                                                                                  *|
|*        http://www.apache.org/licenses/LICENSE-2.0                                *|
|*                                                                                  *|
|*    Unless required by applicable law or agreed to in writing, software           *|
|*    distributed under the License is distributed on an "AS IS" BASIS,             *|
|*    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.      *|
|*    See the License for the specific language governing permissions and           *|
|*    limitations under the License.                                                *|
|*                                                                                  *|
\* -------------------------------------------------------------------------------- */

/* Creates on 2023/5/6. */

import com.redgogh.commons.lang3.iface.TypeMapper;

import java.util.*;

/**
 * `Lists` 是一个工具类，提供了各种对集合（特别是 {@link List} 和 {@link Collection}）
 * 进行操作的方法。这些方法旨在简化对集合的常见操作，如判断集合是否为空、复制集合、
 * 创建集合实例、合并集合、过滤集合、以及计算集合的交集和差集等。
 *
 * <p>该类中的所有方法都是静态方法，能够直接调用而无需实例化对象。这些方法的设计初衷
 * 是为了提高集合操作的简洁性和可读性，避免冗长的集合操作代码。
 *
 * <p>本类的主要特点包括：
 * <ul>
 *     <li>通过简化常见集合操作，提升代码的可读性和维护性。</li>
 *     <li>支持泛型操作，增强了方法的适用性和灵活性。</li>
 *     <li>提供了对集合元素的映射转换和过滤功能，方便集合元素的快速处理。</li>
 *     <li>集成了 {@link TypeMapper} 接口，允许通过 Lambda 表达式对集合元素进行
 *         自定义处理。</li>
 * </ul>
 *
 * <p>该工具类尤其适合在需要频繁操作集合的场景中使用，能够极大地减少手动处理集合的代码量。
 *
 * <h2>注意事项</h2>
 * <ul>
 *     <li>部分方法要求传入的集合参数不能为空，否则会抛出 {@link NullPointerException} 异常。</li>
 *     <li>集合的操作大多数是不可变的，即原集合不会被修改，返回的新集合是操作后的结果。</li>
 *     <li>对于需要映射转换的操作，需要传入实现了 {@link TypeMapper} 的 Lambda 表达式
 *         或函数式接口。</li>
 * </ul>
 *
 * <p>本类支持的集合类型包括但不限于 {@link ArrayList}、{@link LinkedList}、{@link HashSet} 等。
 *
 * <h2>使用示例</h2>
 * <pre>
 *     // 判断集合是否为空
 *     boolean isEmpty = Lists.isEmpty(collection);
 *
 *     // 创建一个包含指定元素的 ArrayList
 *     List<Integer> list = Lists.of(1, 2, 3);
 *
 *     // 计算两个集合的交集
 *     List<Integer> intersection = Lists.intersection(list1, list2);
 * </pre>
 *
 * <p>完整的使用示例和测试用例可以参考项目的测试包下的相关测试类。
 *
 * @author RedGogh
 *
 * @see Collection
 * @see List
 * @see ArrayList
 * @see TypeMapper
 * @since 1.0
 */
public class Lists {

    /**
     * 判断参数 {@code a} 实现了 {@link Collection} 接口的对象实例，是否为空。如果要满足函数
     * 返回 {@code false} 那么该对象不能为 {@code null} 并且该对象的 {@code size} 必须大
     * 于 0。反之返回 {@code true}。<p>
     *
     * <p>
     * 这个函数主要的作用在于判断各种 {@link List}、{@link Set} 等继承了 {@link Collection}
     * 接口的子接口。
     *
     * @param a
     *        一个 {@link Collection} 接口对象实例
     *
     * @return {@code false} 表示集合不为空，集合内有真实的数据。反之 {@code true} 表示集合
     *         内是空的，没有真实的任何数据。
     *
     * @see List
     * @see Set
     */
    public static <E> boolean isEmpty(Collection<E> a) {
        return a == null || a.isEmpty();
    }

    /**
     * @return 返回 {@link Collection} 接口实例中的第一个元素，如果 {@link Collection}
     *         对象为 {@code null} 或 {@code size} == 0 那么则会返回 {@code null}
     */
    @SuppressWarnings("unchecked")
    public static <E> E beg(Collection<E> collection) {
        if (collection == null || collection.isEmpty())
            return null;
        Object[] array = collection.toArray();
        return (E) array[0];
    }

    /**
     * @return 返回 {@link Collection} 接口实例中的最后一个元素，如果 {@link Collection}
     *         对象为 {@code null} 或 {@code size} == 0 那么则会返回 {@code null}
     */
    @SuppressWarnings("unchecked")
    public static <E> E end(Collection<E> collection) {
        if (collection == null || collection.isEmpty())
            return null;
        Object[] array = collection.toArray();
        return (E) array[array.length - 1];
    }

    /**
     * 传入一个 {@link List} 对象，拷贝参数 {@code a} 中的数据到新的 {@link ArrayList} 对象中，数据拷贝
     * 过去以后，修改原来的 {@link List} 对象或修改新分配到对象，并不会影响两个对象之间的数据。
     *
     * @param a 需要拷贝的数据
     * @return 拷贝后的新 {@link ArrayList} 对象实例
     */
    public static <E> List<E> copy(Collection<E> a) {
        return new ArrayList<>(a);
    }

    /**
     * @return 分配一个空的 {@link ArrayList} 集合对象实例。
     */
    public static <E> List<E> of() {
        return new ArrayList<>();
    }

    /**
     * 通过传入的泛型可变参数去分配一个 {@link ArrayList} 集合对象实例。泛型可变参数不能为空
     * 否则会抛出 {@link NullPointerException} 异常。
     *
     * @param a
     *        泛型可变参数数组
     *
     * @return 一个新的 {@link ArrayList} 对象实例
     *
     * @throws NullPointerException
     *         如果泛型参数为空值
     *
     * @see #of(Collection)
     */
    @SafeVarargs
    public static <E> ArrayList<E> of(E... a) {
        return of(Arrays.asList(a));
    }

    /**
     * 截取数组 {@code a} 通过 {@code off} 和 {@code len} 两个参数去截取，截取出来的新数组会
     * 分配成一个可变的 {@link ArrayList} 对象实例。
     *
     * @param a
     *        泛型可变参数数组
     *
     * @param off
     *        泛型数组开始的索引位置，偏移量。
     *
     * @param len
     *        要截取的长度，这个长度范围应该在 <= {@code a}.length()。如果长度超出 {@code a} 数组
     *        的大小，会抛出 {@link ArrayIndexOutOfBoundsException} 异常。
     *
     * @return 一个新的 {@link ArrayList} 对象实例
     *
     * @throws NullPointerException
     *         如果泛型参数为空值。
     *
     * @throws ArrayIndexOutOfBoundsException
     *         如果 {@code len} 超出整个 {@code a} 数组大小的长度。
     *
     * @see #of(Collection)
     */
    @SuppressWarnings("unchecked")
    public static <E> ArrayList<E> of(E[] a, int off, int len) {
        return (ArrayList<E>) of(Arrays.asList(a, off, len));
    }

    /**
     * 通过传入的 {@link Collection} 实例去分配一个 {@link ArrayList} 集合对象实例。{@code collection} 参数不能为空
     * 否则会抛出 {@link NullPointerException} 异常。
     *
     * @param collection
     *        实现了 {@link Collection} 接口的对象实例，如 {@link ArrayList}、{@link LinkedList} 等
     *        对象实例皆实现了 {@link Collection} 接口。
     *
     * @return 一个新的 {@link ArrayList} 对象实例
     *
     * @throws NullPointerException
     *         如果 {@code collection} 参数为空值
     *
     * @see ArrayList#ArrayList(Collection)
     */
    public static <E> ArrayList<E> of(Collection<? extends E> collection) {
        return new ArrayList<>(collection);
    }

    /**
     * 合并两个实现了 {@link Collection} 接口的对象实例，通过传入顺序合并两个对象成一个新的 {@link ArrayList} 对象
     * 实例。两个 {@link Collection} 不能为空，否则会抛出 {@link NullPointerException} 异常。
     *
     * @param a
     *        实现了 {@link Collection} 接口的对象实例，如 {@link ArrayList}、{@link LinkedList} 等
     *        对象实例皆实现了 {@link Collection} 接口。

     * @param b
     *        另一个实现了 {@link Collection} 接口的对象的实例
     *
     * @return 一个新的 {@link ArrayList} 对象实例
     *
     * @throws NullPointerException
     *         如果 {@code a} 或者 {@code b} 参数为空值
     *
     * @see ArrayList#ArrayList(Collection)
     */
    public static <E> List<E> of(Collection<? extends E> a, Collection<? extends E> b) {
        List<E> ret = of();
        ret.addAll(a);
        ret.addAll(b);
        return ret;
    }

    /**
     * #brief: 创建一个同步的空列表
     *
     * <p>该方法创建一个新的空列表，并将其包装为同步列表，以确保线程安全。
     * 调用 `synchronizedList` 方法来实现同步包装。
     *
     * @param <E> 列表元素的类型
     * @return 包装后的同步空列表
     */
    public static <E> List<E> newSynchronizedList() {
        return synchronizedList(of());
    }

    /**
     * #brief: 将现有列表包装为同步列表
     *
     * <p>该方法将提供的列表包装为同步列表，以确保在多线程环境下对列表的操作是线程安全的。
     * 使用 `Collections.synchronizedList` 方法进行包装。
     *
     * @param list 要包装的原始列表
     * @param <E> 列表元素的类型
     * @return 包装后的同步列表
     */
    public static <E> List<E> synchronizedList(List<E> list) {
        return Collections.synchronizedList(list);
    }

    /**
     * 通过实现 {@link TypeMapper} 的 Lambda 接口，将一个对象转换成另一个对象实例，并
     * 批量添加到新的 {@link List} 集合中。
     *
     * @param builder
     *        Lambda 函数实现接口，或者也可以通过实现 {@link TypeMapper} 接口的方式
     *        完成这个参数。
     *
     * @param a
     *        输入数组
     *
     * @return 返回通过 {@link TypeMapper} 转换后的集合实例。
     */
    public static <T, R> List<R> map(T[] a, TypeMapper<T, R> builder) {
        return map(of(a), builder);
    }

    /**
     * 通过实现 {@link TypeMapper} 的 Lambda 接口，将一个对象转换成另一个对象实例，并
     * 批量添加到新的 {@link List} 集合中。
     *
     * @param builder
     *        Lambda 函数实现接口，或者也可以通过实现 {@link TypeMapper} 接口的方式
     *        完成这个参数。
     *
     * @param collection
     *        实现了 {@link Collection} 接口的对象实例
     *
     * @return 返回通过 {@link TypeMapper} 转换后的集合实例。
     */
    public static <T, R> List<R> map(Collection<T> collection, TypeMapper<T, R> builder) {
        List<R> retval = null;
        if (collection != null) {
            retval = of();
            for (T obj : collection)
                retval.add(builder.call(obj));
        }
        return retval;
    }

    /**
     * #brief：计算两个集合之间的交集部分
     *
     * <p>计算两个集合之间的交集部分，参数 {@code a} 和 {@code b} 是两个
     * 相同的数据类型集合。通过该函数可以取得两个集合之间的交集部分，并返回
     * 一个新的集合对象。
     *
     * @param a 集合 a
     * @param b 集合 b
     *
     * @return 计算后返回：两个集合之间的交集
     */
    public static <E> List<E> intersection(Collection<E> a, Collection<E> b) {
        List<E> lcopy = copy(a);
        lcopy.retainAll(b);
        return lcopy;
    }

    /**
     * #brief：计算两个集合之间的交集部分，支持集合 {@code b} 映射
     *
     * <p>计算两个集合之间的交集部分，参数 {@code a} 和 {@code b} 是两个
     * 相同的数据类型集合。通过该函数可以取得两个集合之间的交集部分，并返回
     * 一个新的集合对象。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 首先我们有一个包含 ["1", "2", "3"] 的整数集合，但它是字符串类型
     *     var numstrs = of("1", "2", "3");
     *
     *     // 然后我们还有一个整数类型集合，[1, 2, 3, 4, 5, 6]
     *     var numints = of(1, 2, 3, 4, 5, 6);
     *
     *     // 根据数字类型，取差集，预期结果为：[1, 2, 3]
     *     var ret = intersection(numints, numstrs, Objects::atoi); // 通过 atoi 将 string 转为 int 做比较
     * </pre>
     *
     * <p>可执行测试用例 'CollectsTest#intersectionTest2'（在 test 包下）
     *
     * @param a         集合 a
     * @param b         集合 b，类型可以是任意对象
     * @param bMapper   映射操作，将集合 {@code b} 中的数据对应类型
     *                  映射为 {@code a} 数据类型。
     *
     * @return 计算后返回：两个集合之间的交集
     */
    public static <E, M> List<E> intersection(Collection<E> a, Collection<M> b, TypeMapper<M, E> bMapper) {
        return intersection(a, map(b, bMapper));
    }

    /**
     * #brief：计算两个集合之间的交集部分，支持集合 {@code b} & {@code b} 映射
     *
     * <p>计算两个集合之间的交集部分，参数 {@code a} 和 {@code b} 是两个
     * 相同的数据类型集合。通过该函数可以取得两个集合之间的交集部分，并返回
     * 一个新的集合对象。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 首先我们有一个包含 ["1", "2", "3"] 的整数集合，但它是字符串类型
     *     var numstrs = of("1", "2", "3");
     *
     *     // 然后我们还有一个整数类型集合，[1, 2, 3, 4, 5, 6]
     *     var numints = of(1, 2, 3, 4, 5, 6);
     *
     *     // 根据数字类型，取差集，预期结果为：[1, 2, 3]
     *     var ret = intersection(numints, Objects::atoi, numstrs, Objects::atoi); // 通过 atoi 将 string 转为 int 做比较
     * </pre>
     *
     * @param a           完整数据集
     * @param aMapper     映射操作，将 {@code a} 中的数据对应类型映射
     *                    为 {@code E} 泛型数据类型。
     * @param b           部分数据集，可以是任意对象
     * @param bMapper     映射操作，将 {@code b} 中的数据类型映射
     *                    为 {@code E} 泛型数据类型
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <M1, M2, E> List<E> intersection(Collection<M1> a, TypeMapper<M1, E> aMapper,
                                              Collection<M2> b, TypeMapper<M2, E> bMapper) {
        return intersection(map(a, aMapper), map(b, bMapper));
    }

    /**
     * #brief：获取两个集合之间的差集
     *
     * <p>获取两个集合之间的差集，参数 {@code a} 为数据比较完整的集合列表，
     * 参数 {@code b} 为只有部分的数据列表。根据两个中的数据内容计算
     * 两个集合之间的差集。
     *
     * @param a 完整数据集
     * @param b 部分数据集
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <E> List<E> diff(Collection<E> a, Collection<E> b) {
        List<E> lcopy = copy(a);
        lcopy.removeAll(b);
        return lcopy;
    }

    /**
     * #brief：获取两个集合之间的差集，支持集合映射转换
     *
     * <p>获取两个集合之间的差集，参数 {@code a} 为数据比较完整的集合列表，
     * 参数 {@code b} 为只有部分的数据列表。根据两个中的数据内容计算
     * 两个集合之间的差集。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 首先我们有一个包含 ["1", "2", "3"] 的整数集合，但它是字符串类型
     *     var numstrs = of("1", "2", "3");
     *
     *     // 然后我们还有一个整数类型集合，[1, 2, 3, 4, 5, 6]
     *     var numints = of(1, 2, 3, 4, 5, 6);
     *
     *     // 根据数字类型，取差集，预期结果为：[4，5，6]
     *     var ret = diff(numints, numstrs, Objects::atoi); // 通过 atoi 将 string 转为 int 做比较
     * </pre>
     *
     * <p>可执行测试用例 'CollectsTest#diffMapperTest'（在 test 包下）
     *
     * @param a         完整数据集
     * @param b         部分数据集，可以是任意对象
     * @param bMapper   映射操作，将 {@code b} 中的数据对应类型
     *                  映射为 {@code a} 数据类型。
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <E, M> List<E> diff(Collection<E> a, Collection<M> b, TypeMapper<M, E> bMapper) {
        return diff(a, map(b, bMapper));
    }

    /**
     * #brief：获取两个集合之间的差集，支持集合映射转换
     *
     * <p>获取两个集合之间的差集，参数 {@code a} 为数据比较完整的集合列表，
     * 参数 {@code b} 为只有部分的数据列表。根据两个中的数据内容计算
     * 两个集合之间的差集。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 首先我们有一个包含 ["1", "2", "3"] 的整数集合，但它是字符串类型
     *     var numstrs1 = of("1", "2", "3");
     *
     *     // 然后我们还有一个包含 ["2", "3", "4"]
     *     var numstrs2 = of("2", "3", "4");
     *
     *     // 根据数字类型，取差集，预期结果为：["1", "4"]
     *     var ret = diff(numstrs1, Objects::atoi, numstrs2, Objects::atoi); // 通过 atoi 将 string 转为 int 做比较
     * </pre>
     *
     * @param a           完整数据集
     * @param aMapper     映射操作，将 {@code a} 中的数据对应类型映射
     *                    为 {@code E} 泛型数据类型。
     * @param b           部分数据集，可以是任意对象
     * @param bMapper     映射操作，将 {@code b} 中的数据类型映射
     *                    为 {@code E} 泛型数据类型
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <M1, M2, E> List<E> diff(Collection<M1> a, TypeMapper<M1, E> aMapper,
                                               Collection<M2> b, TypeMapper<M2, E> bMapper) {
        return diff(map(a, aMapper), map(b, bMapper));
    }

    /**
     * #brief：计算两个集合之间的对称差集
     *
     * <p>计算两个集合之间的对称差集，参数 {@code a} & {@code b} 两个集合
     * 中一个集合与另一个集合相差的部分称为`差集`。两个集合共同相差的部分称作为
     * `对称差集`。
     *
     * @param a 集合 a
     * @param b 集合 b
     *
     * @return 计算后返回：两个集合之间的`对称差集`
     */
    @SuppressWarnings({"SlowAbstractSetRemoveAll"})
    public static <E> List<E> symdiff(Collection<E> a, Collection<E> b) {
        Set<E> symmdiff = Sets.of();
        symmdiff.addAll(a);
        symmdiff.addAll(b);

        /* 计算交集 */
        List<E> inter = intersection(a, b);

        symmdiff.removeAll(inter);
        return (List<E>) of(symmdiff);
    }

    /**
     * #brief：计算两个集合之间的对称差集
     *
     * <p>计算两个集合之间的对称差集，参数 {@code a} & {@code b} 两个集合
     * 中一个集合与另一个集合相差的部分称为`差集`。两个集合共同相差的部分称作为
     * `对称差集`。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 先创建集合 A
     *     var A = of(2, 3, 4);
     *     // 然后创建集合 B
     *     var B = of("1", "2", "3");
     *     // 最后取对称差集部分，预期结果：[1，4]
     *     symdiff(A, B, Objects::atoi); // 通过 atoi 将集合 A 的 String 转为 int 计算对称差集
     * </pre>
     *
     * <p>可执行测试用例 'CollectsTest#symdiffTest'（在 test 包下）
     *
     * @param a         完整数据集
     * @param b         部分数据集，可以是任意对象
     * @param bMapper   映射操作，将 {@code b} 中的数据对应类型
     *                  映射为 {@code a} 数据类型。
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <E, M> List<E> symdiff(Collection<E> a, Collection<M> b, TypeMapper<M, E> bMapper) {
        return symdiff(a, map(b, bMapper));
    }

    /**
     * #brief：计算两个集合之间的对称差集
     *
     * <p>计算两个集合之间的对称差集，参数 {@code a} & {@code b} 两个集合
     * 中一个集合与另一个集合相差的部分称为`差集`。两个集合共同相差的部分称作为
     * `对称差集`。
     *
     * <p>这个函数可能比较难以理解，这里我用一段伪代码来解释这个函数的调用方式以便
     * 理解函数用法：
     * <pre>
     *     // 先创建集合 A
     *     var A = of(2, 3, 4);
     *     // 然后创建集合 B
     *     var B = of("1", "2", "3");
     *     // 最后取对称差集部分，预期结果：[1，4]
     *     symdiff(A, B, Objects::atoi); // 通过 atoi 将集合 A 的 String 转为 int 计算对称差集
     * </pre>
     *
     * <p>可执行测试用例 'CollectsTest#symdiffTest'（在 test 包下）
     *
     * @param a           完整数据集
     * @param aMapper     映射操作，将 {@code a} 中的数据对应类型映射
     *                    为 {@code E} 泛型数据类型。
     * @param b           部分数据集，可以是任意对象
     * @param bMapper     映射操作，将 {@code b} 中的数据类型映射
     *                    为 {@code E} 泛型数据类型
     *
     * @return 计算后返回：两个集合之间的差集
     */
    public static <M1, M2, E> List<E> symdiff(Collection<M1> a, TypeMapper<M1, E> aMapper,
                                                   Collection<M2> b, TypeMapper<M2, E> bMapper) {
        return symdiff(map(a, aMapper), map(b, bMapper));
    }

}
