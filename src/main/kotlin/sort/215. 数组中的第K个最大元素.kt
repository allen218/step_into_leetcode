package sort

/*
给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。

示例 1:

输入: [3,2,1,5,6,4], k = 2
输出: 5
示例: 2:

输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4
*/

fun main() {
    val arr = intArrayOf(3, 2, 1, 5, 6, 4)
    findKthLargest(arr, 2)
}

fun findKthLargest(nums: IntArray, k: Int): Int {
    sortByQuickSort(nums)
    return nums[nums.size - k]
}

fun sortByQuickSort(nums: IntArray) {
    quickSort(nums, 0, nums.size - 1)
}

fun quickSort(nums: IntArray, l: Int, r: Int) {
    // 1. 递归终止条件
    if (l >= r) {
        return
    }

    // 2. 找分区点
    val pivot = partition(nums, l, r)
    println("left, pivot = $pivot")
    traverseIntArray(nums)
    // 3. 对分区点左边的数据进行排序
    quickSort(nums, l, pivot)
    println("right pivot = $pivot")
    traverseIntArray(nums)
    // 4. 对分区点右边的数据进行排序
    quickSort(nums, pivot + 1, r)
}

fun traverseIntArray(nums: IntArray) {
    nums.forEach { print("$it, ") }
    println()
}

fun partition(nums: IntArray, l: Int, r: Int): Int {
    // 随机找参考点，避免出现 N^2 时间复杂度的情况
    val pivot = (l until r).random()
    val pivotVal = nums[pivot]
    var left = l
    var right = r

    while (left <= right) {
        while (nums[left] < pivotVal) left++
        while (nums[right] > pivotVal) right--
        if (left == right) break
        // 前面的循环判断可能会出现 left > right 的情况，这里需要再次判断
        if (left < right) {
            swap(nums, left, right)
            left++
            right--
        }
    }

    return right
}

fun swap(nums: IntArray, i: Int, j: Int) {
    val tmp = nums[i]
    nums[i] = nums[j]
    nums[j] = tmp
}