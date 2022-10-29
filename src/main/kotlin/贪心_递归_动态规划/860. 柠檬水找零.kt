package 贪心_递归_动态规划

/*
在柠檬水摊上，每一杯柠檬水的售价为5美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。
每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。
注意，一开始你手头没有任何零钱。

给你一个整数数组 bills ，其中 bills[i] 是第 i 位顾客付的账。如果你能给每位顾客正确找零，返回true，否则返回 false。

示例 1：

输入：bills = [5,5,5,10,20]
输出：true
解释：
前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
由于所有客户都得到了正确的找零，所以我们输出 true。
*/
fun main() {
    val result = lemonadeChange(intArrayOf(5, 5, 5, 10, 20))
    println(result)
}

// 思路：使用贪心算法，先找 20，再找 10，最后找 5
// 1. 存储面值与数量的映射
val valueAndCount = HashMap<Int, Int>()
fun lemonadeChange(bills: IntArray): Boolean {
    for (i in bills.indices) {
        var cash = bills[i]
        if (valueAndCount[cash] == null) {
            valueAndCount[cash] = 1
        } else {
            valueAndCount[cash] = valueAndCount[cash]!! + 1
        }
        // 2. 判断是否满足找零需求
        if (!exchange(bills[i] - 5)) return false
    }

    return true
}

// amount: 待找零的值
fun exchange(amount: Int): Boolean {
    var newAmount = amount
    intArrayOf(20, 10, 5).forEach {
        while (newAmount >= it && valueAndCount[it] != null && valueAndCount[it]!! > 0) {
            valueAndCount[it] = valueAndCount[it]!! - 1
            newAmount -= it
        }
    }

    return newAmount == 0
}
