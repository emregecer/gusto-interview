Your goal is to parse a userLog file and do some analysis on it. The userLog file contains all requests to a server within a specific timeframe.

You are given the following method/url definitions:

    GET /api/users/{user_id}/count_pending_messages
    GET /api/users/{user_id}/get_messages
    GET /api/users/{user_id}/get_friends_progress
    GET /api/users/{user_id}/get_friends_score
    POST /api/users/{user_id}
    GET /api/users/{user_id}

Where user_id is the id of the user calling the backend.

The script/program should output a small analysis of the sample. It should contain the following information for each of the URLs above:

- The number of times the URL was called. 
- Mean (average) response times (connect time + service time)
- Median response times (connect time + service time)
- The output should be a JSON string.


The userLog format is defined as:

{timestamp} {source}[{process}]: at={log_level} method={http_method} path={http_path} host={http_host} fwd={client_ip} dyno={responding_dyno} connect={connection_time}ms service={processing_time}ms status={http_status} bytes={bytes_sent}

Example:

    2014-01-09T06:16:53.916977+00:00 heroku[router]: at=info method=GET path=/api/users/1538823671/count_pending_messages host=mygame.heroku.com fwd="208.54.86.162" dyno=web.11 connect=7ms service=9ms status=200 bytes=33
    2014-01-09T06:18:53.014475+00:00 heroku[router]: at=info method=GET path=/api/users/78475839/count_pending_messages host=mygame.heroku.com fwd="208.54.86.162" dyno=web.10 connect=8ms service=10ms status=200 bytes=33
    2014-01-09T06:20:33.142889+00:00 heroku[router]: at=info method=GET path=/api/users/847383/count_pending_messages host=mygame.heroku.com fwd="208.54.86.162" dyno=web.10 connect=7ms service=10ms status=200 bytes=33

Given the above three userLog lines, we would expect output like:

    {
        "request_identifier": "GET /api/users/{user_id}/count_pending_messages",
        "called": 3,
        "response_time_mean": 17.0,
        "response_time_median": 17.0,
        "dyno_mode": "web.10"
    }