#!/bin/bash

RANDOM_NAME=$(curl -s https://random-data-api.com/api/name/random_name | jq -r '.first_name')

curl -XPOST http://localhost:8080/people \
  -H 'Content-Type: application/json' \
  -d "{\"name\":\"${RANDOM_NAME}\"}"
