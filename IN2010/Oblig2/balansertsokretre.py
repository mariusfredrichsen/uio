sorted_array = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]


def create_balanced_tree(sorted_array, output_array):
    if len(sorted_array) <= 1:
        if len(sorted_array) == 1:
            output_array.append(sorted_array[0])
        return

    sublist1 = sorted_array[: len(sorted_array) // 2]
    sublist2 = sorted_array[len(sorted_array) // 2 + 1 :]
    sublist3 = [sorted_array[len(sorted_array)//2]]
    create_balanced_tree(sublist3, output_array)
    create_balanced_tree(sublist2, output_array)
    create_balanced_tree(sublist1, output_array)
    return output_array
    


print(create_balanced_tree(sorted_array, []))