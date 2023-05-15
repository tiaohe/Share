
# 具体方法看api参数
### 基本操作方法：
opsForValue()：返回操作字符串值的ValueOperations接口实例。
opsForList()：返回操作列表的ListOperations接口实例。
opsForSet()：返回操作集合的SetOperations接口实例。
opsForZSet()：返回操作有序集合的ZSetOperations接口实例。
opsForHash()：返回操作哈希表的HashOperations接口实例。
### 字符串操作方法（ValueOperations）：
set(key, value)：设置指定键的值。
get(key)：获取指定键的值。
increment(key, delta)：将指定键的值递增指定的增量。
append(key, value)：将指定的值追加到键的值末尾。
size(key)：获取指定键的值的长度。
### 列表操作方法（ListOperations）：
leftPush(key, value)：将值从列表的左侧推入。
rightPop(key)：从列表的右侧弹出一个值。
range(key, start, end)：获取列表指定范围内的值。
### 集合操作方法（SetOperations）：
add(key, values)：将一个或多个值添加到集合中。
members(key)：获取集合中的所有成员。
### 有序集合操作方法（ZSetOperations）：
add(key, value, score)：将一个值及其分数添加到有序集合中。
range(key, start, end)：根据索引范围获取有序集合的值。
### 哈希表操作方法（HashOperations）：
put(key, hashKey, value)：将哈希表中指定的键值对放入。
get(key, hashKey)：获取哈希表中指定键的值。