package com.zengcanxiang.baseAdapter.interFace;

import java.util.List;

/**
 * adapter规范数据操作接口
 *
 * @author zengcx
 */
public interface DataHelper<T> {

    /**
     * 判断是否有数据
     *
     * @return {@code false} 无数据
     */
    boolean isEnabled();

    /**
     * 添加单个数据到列表头部
     *
     * @param data 数据
     */
    void addItemToHead(T data);

    /**
     * 添加单个数据到列表尾部
     *
     * @param data 数据
     * @return 操作结果
     */
    boolean addItemToLast(T data);

    /**
     * 添加数据集到列表头部
     *
     * @param datas 数据集
     * @return 操作结果
     */
    boolean addItemsToHead(List<T> datas);

    /**
     * 添加数据集到列表尾部
     *
     * @param datas 数据集
     * @return 操作结果
     */
    boolean addItemsToLast(List<T> datas);

    /**
     * 添加数据集合到指定位置
     *
     * @param startPosition 数据添加的位置
     * @param datas         数据集
     * @return 操作结果
     */
    boolean addAll(int startPosition, List<T> datas);

    /**
     * 添加单个数据到指定位置
     *
     * @param startPosition 数据添加的位置
     * @param data          数据
     */
    void add(int startPosition, T data);

    /**
     * 获取index对于的数据
     *
     * @param index 数据座标
     * @return 数据对象
     */
    T getData(int index);

    /**
     * 将某一个数据修改
     *
     * @param oldData 旧的数据
     * @param newData 新的数据
     */
    void alterObj(T oldData, T newData);

    /**
     * 修改对应的位置的数据
     *
     * @param index 修改的位置
     * @param data  要代替的的数据
     */
    void alterObj(int index, T data);

    /**
     * 删除对应的数据
     *
     * @param data 数据
     * @return 操作结果
     */
    boolean remove(T data);

    /**
     * 删除对应位置的数据
     *
     * @param index 删除的位置
     */
    void removeToIndex(int index);

    /**
     * 替换所有数据
     *
     * @param datas 数据集
     */
    void replaceAll(List<T> datas);

    /**
     * 清除所有
     */
    void clear();

    /**
     * 判断数据集合中是否包含这个对象<br>
     * {@link List#contains(Object)}
     *
     * @param data 判断对象
     * @return {@code true}包含该对象|{@code false}不包含该对象
     */
    boolean contains(T data);


}
