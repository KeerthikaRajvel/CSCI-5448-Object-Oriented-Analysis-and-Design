from flask import Flask, render_template, request
from flask import jsonify

app=Flask(__name__)

@app.route('/')
def index():
   return render_template('index.html')

@app.route('/signup',methods=['GET'])
def signup():
   return render_template('signup.html')

@app.route('/add_user',methods=['GET','POST'])
def add_user():
   if request.method == 'POST':
      result = request.form.to_dict()
      connection.insert_user(result)
   return render_template('index.html')

@app.route('/dashboard',methods=['GET','POST'])
def dashboard():
   if request.method == 'POST':
      result=connection.get_books()
      return render_template('dashboard.html',books=result)

@app.route('/lend',methods=['GET','POST'])
def search():
   if request.method == 'POST':
      book=request.form["book-name"]
      if book!="":
         connection.add_book(book)
      result = connection.get_books()
      return render_template('dashboard.html', books=result)

@app.route('/bookDetails/<book>')
def bookDetails(book):
   details=connection.search(book)
   tweets=twitterAPI.get_tweets(book,details[0]["authors"])
   return render_template('bookDetails.html',book_details=details,target=tweets)

if __name__ == '__main__':
   app.run(debug = True,port=3400)