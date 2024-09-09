from flask import Flask, render_template, request

app = Flask(__name__)

def fahrenheit_to_celsius(fahrenheit):
    return (fahrenheit - 32) * 5 / 9

def celsius_to_fahrenheit(celsius):
    return (celsius * 9 / 5) + 32

@app.route('/', methods=['GET', 'POST'])
def index():
    result = ""
    if request.method == 'POST':
        choice = request.form.get('choice')
        if choice == '1':
            try:
                f_temp = float(request.form.get('fahrenheit'))
                c_temp = fahrenheit_to_celsius(f_temp)
                result = f"{f_temp}°F is approximately {c_temp:.2f}°C."
            except ValueError:
                result = "Invalid input. Please enter a valid numeric value."
        elif choice == '2':
            try:
                c_temp = float(request.form.get('celsius'))
                f_temp = celsius_to_fahrenheit(c_temp)
                result = f"{c_temp}°C is approximately {f_temp:.2f}°F."
            except ValueError:
                result = "Invalid input. Please enter a valid numeric value."
        else:
            result = "Invalid choice. Please select 1 or 2."

    return render_template('index.html', result=result)

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=8080)
