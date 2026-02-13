def sum_lines(input_text):
    lines = input_text.strip().split('\n')  # Skip first line (header)
    results = []
    
    for line in lines:
        if line.strip():
            # Replace comma with dot for proper float conversion
            line = line.replace(',', '.')
            # Split by + and sum the numbers
            total = sum(float(num) for num in line.split('+'))
            results.append(total)
    
    return results

# Example usage
input_data = """1+3,5+0+3+3
1+0,5+3+3+2
1+4+0+0+0
1+4+3+1+3
1+4+0+1+3
1+0+0+3+3
1+0,5+0,5+3+2
1+4+0+0+3
1+0+0+0+0
1+1+0+0+0
1+3+0+0+2
1+3+0+0+0
1+0+0+0+0
1+1+0+0,5+3
1+1+0+1+2
1+0+0+1+2
1+1+1+2+2
1+0+2+0+3+2
1+2+0+0+2
0,5+2+3+0+0
1+4+4+3+3
1+4+0+0,5+0
1+0+2+3+2
1+0+0+3+3
1+1+4+1+2
1+4+4+0+0,5
0+0+0+0+0
1+0,5+0+1+2
1+0+0+0+2
1+1+3+1+2
1+1+0+1+2
1+1+0+0+0
1+4+0+3+2
1+4+0+0+0
1+0+1+0+2"""

sums = sum_lines(input_data)
for i, total in enumerate(sums, 1):
    print(f"{total}")