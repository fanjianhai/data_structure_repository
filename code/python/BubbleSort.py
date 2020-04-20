from typing import List


def bubble_sort(a: List[int]):
    """冒泡排序
    """
    length = len(a)
    if length <= 1:
        return

    last_exchange = 0  # 最后一次交换的位置
    sort_border = length - 1  # 无需数据的边界
    for i in range(length):
        flag = False  # 提前退出标志位
        for j in range(sort_border):
            if a[j] > a[j + 1]:
                a[j], a[j + 1] = a[j + 1], a[j]

                flag = True  # 此次冒泡有数据交换
                last_exchange = j  # 更新最后一次交换的位置

        sort_border = last_exchange
        if not flag:
            break


if __name__ == '__main__':
    a = list([3, 4, 1, 2, 7, 5])
    print(a)
    bubble_sort(a)
    print(a)