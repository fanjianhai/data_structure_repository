class XFArray:

    def __init__(self, capacity: int):
        self._data = []
        self._capacity = capacity

    def __getitem__(self, item, position: int) -> object:
        return self._data[position]

    def __setitem__(self, index: int, value: object):
        self._data[index] = value

    def __len__(self) -> int:
        return len(self._data)

    def __iter__(self):
        for item in self._data:
            yield item

    def find(self, index: int) -> object:
        try:
            return self._data[index]
        except IndexError:
            return None

    def delete(self, index: int) -> bool:
        try:
            self._data.pop(index)
            return True
        except IndexError:
            return False

    def insert(self, index: int, value: int) -> bool:
        if len(self) >= self._capacity:
            return False
        else:
            return self._data.insert(index, value)

    def print_all(self):
        for item in self:
            print(item)


if __name__ == '__main__':
    array = XFArray(5)
    array.insert(0, 3)
    array.insert(0, 4)
    array.insert(1, 5)
    array.insert(3, 9)
    array.insert(3, 10)
    assert array.insert(0, 100) is False
    assert len(array) == 5
    assert array.find(1) == 5
    assert array.delete(4) is True
    array.print_all()
