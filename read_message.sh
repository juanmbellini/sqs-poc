awslocal sqs receive-message --queue-url=$QUEUE_URL | jq ‘.Messages[].ReceiptHandle’ | xargs -S 1024 -I X awslocal sqs delete-message --queue-url=$QUEUE_URL --receipt-handle X
