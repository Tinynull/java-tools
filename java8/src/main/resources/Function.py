

"""
方法是值传递的，但是对象不一样。
如果一个方法没有返回值，虽然没有return语句，实际上也是返回了值的，且值为None。
"""


# 函数名必须以下划线(_)或字母开头，可
# 以包含任意字母、数字或下划线的组合，不能使用任何的标点符号。
# 函数名是区分大小写的。
# 函数名不能是保留字
def fib(n):
    """
    斐波那契函数，打印 0 - n 中所有斐波那契函数。

    :param n: the number
    :return:
    """
    a, b = 0, 1
    while a < n:
        print a,
        a, b = b, a + b


def fib2(n):  # return Fibonacci series up to n
    """Return a list containing the Fibonacci series up to n."""
    result = []
    a, b = 0, 1
    while a < n:
        result.append(a)  # see below
        a, b = b, a + b
    return result


# 函数带参数，参数可以有默认值
# def ask_ok(prompt, retries=4, complaint='Yes or no, please!'):
#     while True:
#         ok = raw_input(prompt)
#         if ok in ('y', 'ye', 'yes'):
#             return True
#         if ok in ('n', 'no', 'nop', 'nope'):
#             return False
#         retries -= 1
#         if retries < 0:
#             raise IOError('refusenik user')
#         print complaint


# ask_ok('Do you really want to quit?')
# ask_ok('OK to overwrite the file?', 2)
# ask_ok('OK to overwrite the file?', 2, 'Come on, only yes or no!')


# Important warning: The default value is evaluated only once. This makes a
# difference when the default is a mutable object such as a list, dictionary,
# or instances of most classes. For example, the following function accumulates
# the arguments passed to it on subsequent calls:
def f(a, L=[]):
    L.append(a)
    return L



'''
If you don’t want the default to be shared between subsequent
calls, you can write the function like this instead:
'''


def f(a, L=None):
    if L is None:
        L = []
    L.append(a)
    return L


def parrot(voltage, state='a stiff', action='voom', type='Norwegian Blue'):
    print "-- This parrot wouldn't", action,
    print "if you put", voltage, "volts through it."
    print "-- Lovely plumage, the", type
    print "-- It's", state, "!"


# print '-' * 30
# parrot(1000)  # 1 positional argument
# print '-' * 30
# parrot(voltage=1000)  # 1 keyword argument
# print '-' * 30
# parrot(voltage=1000000, action='VOOOOOM')  # 2 keyword arguments
# print '-' * 30
# parrot(action='VOOOOOM', voltage=1000000)  # 2 keyword arguments
# print '-' * 30
# parrot('a million', 'bereft of life', 'jump')  # 3 positional arguments
# print '-' * 30
# parrot('a thousand', state='pushing up the daisies')  # 1 positional, 1 keyword
# print '-' * 30


def cheeseshop(kind, *arguments, **keywords):
    """
    *name 类型的参数必须在 **name之前。
    :param kind: 表示普通的一个函数。
    :param arguments: *arguments 表示一个元组（tuple）
    :param keywords: **keywords 表示一个字典（dictionary）
    """
    print "-- Do you have any", kind, "?"
    print "-- I'm sorry, we're all out of", kind
    for arg in arguments:
        print arg
    print "-" * 40
    keys = sorted(keywords.keys())
    for kw in keys:
        print kw, ":", keywords[kw]


# cheeseshop("Limburger", "It's very runny, sir.",
#            "It's really very, VERY runny, sir.",
#            shopkeeper='Michael Palin',
#            client="John Cleese",
#            sketch="Cheese Shop Sketch")
