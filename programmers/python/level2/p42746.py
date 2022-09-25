# 미해결

def solution(numbers):
    answer = ''
    
    num_map = {str(key):[] for key in range(9, -1, -1) }
    for number in numbers:
        s_number = str(number)
        num_map[s_number[0]].append(s_number)
        
    for num_key in num_map:
        eq_nums = num_map[num_key]
        
        if not eq_nums:
            continue
        
        if len(eq_nums) > 1:
            eq_nums.sort()
            sorted_nums = [eq_nums.pop(0)]
            
            for num in eq_nums:
                is_pass = False
                
                for i in range(len(sorted_nums)):
                    
                    if int(num + sorted_nums[i]) >= int(sorted_nums[i] + num):
                        sorted_nums.insert(i, num)
                        is_pass = True
                        break
                    
                if not is_pass:
                    sorted_nums.append(num)
            for sn in sorted_nums:
                answer += sn
        else:
            answer += eq_nums[0]
    
    count = 0

    for i in range(len(answer) - 1):
        if answer[i] == '0':
            count += 1
        else: break
    
    return answer[count:]

'''
[232,23] : 23/232 : (23232)
[212,21] : 212/21 : (21221)
[70,0,0,0,0] : 70/0/0/0/0 : (700000)
23, 232, 21, 212
'''

print(solution([0,0,0,0]))