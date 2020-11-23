
import math
from flask import Flask, request, jsonify
import pandas_datareader.data as web
import numpy as np
import pandas as pd
from sklearn.preprocessing import MinMaxScaler
from keras import backend as K
import yfinance as yf
from datetime import date
import pandas as pd
import os
from keras.models import load_model

app = Flask(__name__)
model = load_model("model.h5")

# @app.route('/')
# def home():
#     return 'index.html'

@app.route('/',methods=['GET'])
def predict():

    args = request.args
    name = args['cid']
    # name = "TATAMOTORS.NS"

    details = yf.Ticker(name)

    # Create json file

    data_file = {}
    data_file['Company Info'] = []
    data_file['Prediction'] = []
    data_file['Company Info'].append({
        'Website': details.info['website'],
        'Industry':details.info['industry'],
        'Employee': details.info['fullTimeEmployees'],
        'Discription':details.info['longBusinessSummary']
    })

    if name!="TATAMOTORS.NS":
        data_file['Prediction'].append("Coming Soon...")
        return jsonify(data_file)


    # Get the stock quote
    df = web.DataReader(name, data_source='yahoo',start = '2000-01-25', end = date.today())

    df['Date'] = df.index

    # Create new dataframe with only close coloumn
    data = df.filter(['Adj Close'])
    # Convert dataframe to numpy array
    dataset = data.values
    # Get the number of rows to train the model on
    training_data_len = math.ceil( len(dataset) * 0.99)

    # Scale the data
    scaler = MinMaxScaler(feature_range=(0,1))
    scaled_data = scaler.fit_transform(dataset)

    # Create training datasets
    # Create the scaled training datasets
    train_data = scaled_data[0:training_data_len,:]
    # Slit x_train and y_train datasets
    x_train = []
    y_train = []
    for i in range(100,len(train_data)):
        x_train.append(train_data[i-100:i,0])
        y_train.append(train_data[i,0])
        # if i<=100:
        #     print(x_train)
        #     print(y_train)
        #     print()

    # convert x_train y_train to numpy arrays
    x_train, y_train = np.array(x_train), np.array(y_train)

    # Reshape the data
    x_train = np.reshape(x_train,(x_train.shape[0], x_train.shape[1],1))
    test_data = scaled_data[training_data_len-100: ,:]
    # Create dataset x_test, y_test 
    x_test = []
    y_test = dataset[training_data_len: ,:]
    for i in range(100, len(test_data)):
        x_test.append(test_data[i-100:i, 0])


    x_test = x_test[-1:]

    x_test = np.array(x_test)

    # Reshape the data
    x_test = np.reshape(x_test,(x_test.shape[0],x_test.shape[1],1))

    predictions = []
    for i in range(5):
        x_next = model.predict(x_test)
        predictions.append(x_next[0])
        x_test = np.append(x_test[0][1:],x_next)
        x_test = np.reshape(x_test,(-1,100))
        x_test = np.reshape(x_test,(x_test.shape[0],x_test.shape[1],1))

    predictions = np.array(predictions)

    # Get model prediction price value
    predictions = scaler.inverse_transform(predictions)
    predictions = predictions.flatten()

    data_file['Prediction'].append({
        '1st day':str(predictions[0]),
        '2nd day': str(predictions[1]),
        '3rd day': str(predictions[2]),
        '4th day': str(predictions[3]),
        '5th day': str(predictions[4])
    })
    
    return jsonify(data_file)

@app.route('/train',methods=['GET'])
def train():

    os.system('python model_train.py')
    return "Your model is trained now 😀"

if __name__ == "__main__":
    app.run(debug=True)