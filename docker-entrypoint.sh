#!/bin/bash
set -e
sh -c './wait-for-it.sh database:3306 -t 30'
exec "$@"