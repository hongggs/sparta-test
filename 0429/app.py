from datetime import datetime
from flask import Flask, render_template, jsonify, request
from pymongo import MongoClient

app = Flask(__name__)

client = MongoClient('localhost', 27017)
db = client.test


@app.route('/')
def index():
    posts = list(db.posts.find({}, {'_id': False}))
    return render_template('index.html', posts=posts)


@app.route('/detail/<idx>')
def detail(idx):
    print(idx)
    post = db.posts.find_one({'idx': int(idx)},{'_id': False})
    print(post)
    return render_template('detail.html', post=post)

@app.route('/post', methods=['POST'])
def save_post():
    if 0 >= db.posts.estimated_document_count():
        idx = 0
    else:
        idx = list(db.posts.find({}, sort=[('_id', -1)]).limit(1))[0]['idx'] + 1

    title_receive = request.form['title_give']
    content_receive = request.form['content_give']
    password_receive = request.form['password_give']

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

    return {"result": "success", "msg": "저장 성공"}

@app.route('/post', methods=['DELETE'])
def delete_post():
    idx = request.args.get('idx')
    print(idx)
    db.posts.delete_one({'idx': int(idx)})
    return {"result": "success", "msg":"삭제 성공"}


if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)
