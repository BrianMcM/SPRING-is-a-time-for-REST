#!/bin/bash

URL="http://localhost:31954/quotations"
count_pod1=0
count_pod2=0
count_pod3=0
count_pod4=0
count_pod5=0

for i in {1..100}
do
    response=$(curl -s -X POST "$URL" -H "Content-Type: application/json" -d '{"name":"Jane Doe"}')
    pod=$(echo "$response" | jq -r '.servedBy')

    if [[ "$pod" == "auldfellas-7b84bc968d-9wcw7" ]]; then
        ((count_pod1++))
    elif [[ "$pod" == "auldfellas-7b84bc968d-fqp4l" ]]; then
        ((count_pod2++))
    elif [[ "$pod" == "auldfellas-7b84bc968d-6fpqn" ]]; then
            ((count_pod3++))
    elif [[ "$pod" == "auldfellas-7b84bc968d-wnjxd" ]]; then
            ((count_pod4++))
    elif [[ "$pod" == "auldfellas-7b84bc968d-7m8tz" ]]; then
            ((count_pod5++))


    else
        echo "Unknown pod: $pod"
    fi
done

echo "Responses from auldfellas-7b84bc968d-9wcw7: $count_pod1"
echo "Responses from auldfellas-7b84bc968d-5n7q8: $count_pod2"
echo "Responses from auldfellas-7b84bc968d-9wcw7: $count_pod3"
echo "Responses from auldfellas-7b84bc968d-5n7q8: $count_pod4"
echo "Responses from auldfellas-7b84bc968d-9wcw7: $count_pod5"
