function checkAddBook() {
    if (document.form.isbn.value == "") {
        alert("Пожалуйста, введите международный номер книги!");
        return false;
    } else if (document.form.tittle.value == "") {
        alert("Пожалуйста, введите название книги!");
        return false;
    } else if (document.form.surname.value == "") {
        alert("Пожалуйста, введите фамилию автора!");
        return false;
    } else if (document.form.name.value == "") {
        alert("Пожалуйста, введите имя автора!");
        return false;
    } else if (document.form.middleName.value == "") {
        alert("Пожалуйста, введите отчество автора!");
        return false;
    }  else if (document.form.country.value == "") {
        alert("Пожалуйста, введите страну автора!");
        return false;
    } else if (document.form.genre.value == "") {
        alert("Пожалуйста, выберите жанр книги!");
        return false;
    } else if (document.form.dateEdition.value == "") {
        alert("Пожалуйста, введите дату публикации книги!");
        return false;
    } else if (document.form.placeEdition.value == "") {
        alert("Пожалуйста, введите место публикации книги!");
        return false;
    } else if (document.form.publisher.value == "") {
        alert("Пожалуйста, введите издательство книги!");
        return false;
    } else if (document.form.numberCopies.value == "") {
        alert("Пожалуйста, введите количество экземпляров книги!");
        return false;
    } else if (document.form.image.value == "") {
        alert("Пожалуйста, выберите изображение книги!");
        return false;
    }


    if (document.form.isbn.value.length < 13 ) {
        alert("Международный номер должен состоять из 13 символов!");
        return false;
    } else if (!(/^[0-9]+$/.test(document.form.isbn.value))) {
        alert("Неправильный формат международного номера!");
        return false;
    }

    if (!(/^[A-ZА-Я0-9][a-zа-я0-9\-\№\(\)]+$/.test(document.form.tittle.value))) {
        alert("Название книги должно содержать только буквы, цифры и символы(-,№,(,)) и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.surname.value))) {
        alert("Фамилия должна содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.name.value))) {
        alert("Имя должно содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.middleName.value))) {
        alert("Отчество должно содержать только буквы и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[0-9]+$/.test(document.form.age.value))) {
        alert("Возраст должен содержать только цифры!");
        return false;
    } else if (document.form.age.value < 7 || document.form.age.value > 120) {
        alert("Возраст должен быть в дипазоне от 7 до 120 лет!");
        return false;
    }

    if (document.form.phone.value.length > 17 ) {
        alert("Превышение количества символов в номере телефона!");
        return false;
    } else if (!(/^\+|\d[\d\(\)\ -]{4,14}\d$/.test(document.form.phone.value))) {
        alert("Неправильный формат номера телефона!");
        return false;
    }

    if (!(/^[\w-\.]+@[\w-]+\.[a-z]{2,3}$/.test(document.form.mail.value))) {
        alert("Некорректный адрес электронной почты!");
        return false;
    }

    if (document.form.login.value.length < 5) {
        alert("Имя пользователя должно содержать больше 5 символов!");
        return false;
    } else if (!(/^[A-Za-z][A-Za-z0-9_]+$/.test(document.form.login.value))) {
        alert("Имя пользователя должно содержать только латинские буквы!");
        return false;
    }

    if (document.form.password.value.length < 6) {
        alert("Пароль должен содержать не менее 6 символов!");
        return false;
    } else if (!(/^(?=.*[a-z])(?=.*[A-Z]).{4,}$/.test(document.form.password.value))) {
        alert("Пароль должен содержать не менее одной буквы в каждом регистре и не менее одной цифры!")
        return false;
    }

    if (document.form.password.value != document.form.password2.value) {
        alert("Пароли не совпадают!");
        return false;
    }

}


