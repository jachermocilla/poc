"""
based on : <https://gist.github.com/mylsb/10294040>
"""
import facebook
import requests

def some_action(post):
    print post['message']
    print "----------------------------------------------------"

access_token = '<your access token>'
graph = facebook.GraphAPI(access_token)
posts = graph.get_connections('uplbconfessions', 'posts')
while True:
    try:
        [some_action(post=post) for post in posts['data']]
        posts = requests.get(posts['paging']['next']).json()
    except KeyError:
        break
