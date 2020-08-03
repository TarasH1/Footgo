function putDescTextById() {
    //let desctext = document.getElementById('descriptionText').value;
    let desctext = editor.getData();
    console.log(desctext);
    //let desctext_res = encodeURI(desctext);

    //let params = '?descText=' + desctext_res;
    let params = desctext;
    let apiUrl = '/api/game/' + gameId + '/setdesctext'/* + params*/;

    fetch(apiUrl, {
      method: 'PUT',
      body: params,
      headers: {
        'Content-Type': 'application/json'
      }
    })
    .then(function(response){
        if(response.ok) {
        	document.getElementById('textSuccess').style.display = 'block';
        } else {
        	document.getElementById('textError').style.display = 'block';
        }
        console.log(response)
    })
    .then(function(data) {
    })
}
