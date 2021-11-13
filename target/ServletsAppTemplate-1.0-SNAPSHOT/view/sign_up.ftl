<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sign Up</title>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: Cambria;
        }

        main {
            height: 100vh;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            background: aliceblue;
        }

        .auth-form-container {
            z-index: 1;
            width: 100%;
            position: relative;
            max-width: 430px;
            background: white;
            border-radius: 5px;
            box-shadow: 0px 6px 6px rgba(79, 56, 46 , 0.7);
            padding: 38px 30px;
        }


        label {
            display: block;
            margin-bottom: 3%;
        }
        input {
            text-align: center;
            height: 45px;
            width: 100%;
            font-size: 14px;
            line-height: 16px;
            border-radius: 15px;
            display: block;
        }
        .form-title {
            text-align: center;
            font-weight: 500;
            font-size: 20px;
            line-height: 23px;
            margin-bottom: 38px;
            color: red;
        }
        .form-button{
            width: 100%;
            font-weight: bold;
            font-size: 14px;
            margin-top: 10%;
            display:block;
            height: 45px;
            background: red;
            border-radius: 5px;
            color:#fff;
            text-transform: uppercase;
            text-align: center;
            line-height: 45px;
            cursor: pointer;
            border: none;
        }
        .back-button {
            width: 100%;
            height: 20%;
            font-size: 14px;
            margin-top: 2%;
            display:block;
            background: white;
            color:gray;
            text-align: center;
            cursor: pointer;
            border: none;
        }

    </style>
</head>
<body>
    <main>
        <div class="auth-form-container">
            <div class="form-title">
                <h1>
                    Присоединяйтесь
                </h1>
            </div>
            <form method="post">
                <label>
                    <input name="firstName" placeholder="Имя" type="text">
                </label>
                <label>
                    <input name="lastName" placeholder="Фамилия" type="text">
                </label>
                <label>
                    <input name="email" placeholder="Email" type="email">
                </label>
                <label>
                    <input name="password" placeholder="Пароль" type="password">
                </label>
                <label>
                    <input name="age" placeholder="Возраст" type="number" min="3" max="100">
                </label>
                <input type="submit" class="form-button">
            </form>
            <form id="back" action="/sign-in" method="post">
                <input type="submit" value="Назад" class="back-button">
            </form>
        </div>
    </main>
</body>
</html>