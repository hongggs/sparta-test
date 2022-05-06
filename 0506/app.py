from datetime import datetime

from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient

app = Flask(__name__)

client = MongoClient("mongodb://localhost:27017/")
db = client.test


@app.route('/')
def index():
    posts = list(db.posts.find({}, {'_id': False}))
    return render_template('index.html', posts=posts)


@app.route('/detail/<idx>')
def detail(idx):
    print(idx)
    post = db.posts.find_one({'idx': int(idx)}, {'_id': False})
    print(post)
    return render_template('detail.html', post=post, idx=idx)


@app.route('/articleList', methods=['GET'])
def get_article_list():
    article_list = 0  # todo

    for article in article_list:
        article['reg_date'] = article['reg_date'].strftime('%Y.%m.%d %H:%M:%S')

    return jsonify({"article_list": article_list})


# Create
@app.route('/article', methods=['POST'])
def create_article():
    if 0 >= db.posts.estimated_document_count():
        idx = 0
    else:
        idx = list(db.posts.find({}, sort=[('_id', -1)]).limit(1))[0]['idx'] + 1

    title_receive = request.form['title']
    content_receive = request.form['content']
    password_receive = request.form['pw']

    today = datetime.now()
    reg_date = today.strftime("%Y.%m.%d %H:%M:%S")

    doc = {
        'idx': idx,
        'title': title_receive,
        'content': content_receive,
        'pw': password_receive,
        'reg_date': reg_date
    }

    db.posts.insert_one(doc)

    return {"result": "success"}


# Read
@app.route('/article', methods=['GET'])
def read_article():
    idx = request.args.get('idx')
    article = db.posts.find_one({'idx': int(idx)}, {'_id': False})
    return jsonify({"article": article})


# Update
@app.route('/article', methods=['PUT'])
def update_article():
    title_receive = request.form['title']
    content_receive = request.form['content']
    idx = request.form['idx']

    db.posts.update_one({"idx":int(idx)}, {"$set": {"title":title_receive, "content":content_receive}})
    return {"result": "success"}


# Delete
@app.route('/article', methods=['DELETE'])
def delete_article():
    idx = request.args.get('idx')
    print(idx)
    db.posts.delete_one({'idx': int(idx)})
    return {"result": "success", "msg": "삭제 성공"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
