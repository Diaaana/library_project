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
    } else if (document.form.country.value == "") {
        alert("Пожалуйста, введите страну автора!");
        return false;
    } else if (validateChecks() == false) {
        alert("Пожалуйста, выберите жанр книги!");
        return false;
    } else if (document.form.date_edition.value == "") {
        alert("Пожалуйста, выберите дату издания книги!");
        return false;
    } else if (document.form.place_edition.value == "") {
        alert("Пожалуйста, введите место публикации книги!");
        return false;
    } else if (document.form.publisher.value == "") {
        alert("Пожалуйста, введите издательство книги!");
        return false;
    } else if (document.form.number_copies.value == "") {
        alert("Пожалуйста, введите количество экземпляров книги!");
        return false;
    } else if (document.form.image.value == "") {
        alert("Пожалуйста, выберите изображение книги!");
        return false;
    }

    if (!(/^[0-9]+$/.test(document.form.isbn.value))) {
        alert("Неправильный формат международного номера!");
        return false;
    } else if (document.form.isbn.value.length != 13 ) {
        alert("Международный номер должен состоять из 13 символов!");
        return false;
    }

    if (!(/^[A-ZА-Я0-9][a-zа-я0-9\-\№\(\)\s]+$/.test(document.form.tittle.value))) {
        alert("Название книги должно содержать только буквы, пробел, цифры и символы(-,№,(,)) и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.surname.value))) {
        alert("Фамилия может содержать только буквы и символ "-" и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я\-]+$/.test(document.form.name.value))) {
        alert("Имя может содержать только буквы и символ "-" и начинаться с заглавной буквы!");
        return false;
    }

    if (document.form.middle_name.value != "") {
        if (!(/^[A-ZА-Я][a-zа-я]+$/.test(document.form.middle_name.value))) {
            alert("Отчество должно содержать только буквы и начинаться с заглавной буквы!");
            return false;
        }
    }

    if (!(/^[A-ZА-Я][a-zа-я\-\s]+$/.test(document.form.country.value))) {
        alert("Страна автора может содержать только буквы, пробел и символ "-" и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^(\d{4})\-(0\d|1[012])\-([0-2]\d|3[01])$/.test(document.form.date_edition.value))) {
        alert("Дата введена некорректно!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я\s\-]+$/.test(document.form.place_edition.value))) {
        alert("Место издания книги может содержать только буквы, пробел и символ "-" и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[A-ZА-Я][a-zа-я\-\s]+$/.test(document.form.publisher.value))) {
        alert("Издательство книги может содержать только буквы, пробел и символ "-" и начинаться с заглавной буквы!");
        return false;
    }

    if (!(/^[0-9]+$/.test(document.form.number_copies.value))) {
        alert("Неправильный формат для количества экземпляров книги!");
        return false;
    } else if (document.form.number_copies.value.length > 10000) {
        alert("Неправильный формат для количества экземпляров книги!");
        return false;
    }
}

function validateChecks() {
    var chks = document.getElementsByName('genre');
    var checkCount = 0;
    for (var i = 0; i < chks.length; i++) {
        if (chks[i].checked) {
            checkCount++;
        }
    }
    if (checkCount < 1) {
        return false;
    }
    return true;
}

