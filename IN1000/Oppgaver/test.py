class MyClass:
  def __init__(self):
    self._protected_var = 10

    self.__private_var = 20

  
        
  def get_private(self):
    return self.__private_var
      


obj = MyClass()

print(obj._protected_var)

print(obj.get_private())

print(obj.__private_var)

