function putPlayerById(data) {
        let firstName = document.getElementById('firstName').value;
        let lastName = document.getElementById('lastName').value;
        let number = document.getElementById('number').value;
        let phone = document.getElementById('phone').value;
        let email = document.getElementById('email').value;

    let params = '?firstName=' + firstName
                + '&lastName=' + lastName
                + '&number=' + number
                + '&phone=' + phone
                + "&email=" + email;

    let apiUrl = '/player/' + id + params;
    fetch(apiUrl, {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json',
        body: data
      }
    }).then(function(response){
        console.log(response)
        return  response.json();
    }).then(function(data) {

        console.log(data);
    })

}