function clickFormEvent(i) {
    let formWorker = document.getElementById('worker');
    let formCompany = document.getElementById('company');
    let buttonWorker = document.getElementById('buttonWorker');
    let buttonCompany = document.getElementById('buttonCompany');

    if (i === 'worker') {
        formWorker.style.display = 'block';
        buttonWorker.style.display = 'block'
        formCompany.style.display = 'none';
        buttonCompany.style.display = 'none'
    } else {
        formWorker.style.display = 'none';
        buttonWorker.style.display = 'none'
        formCompany.style.display = 'block';
        buttonCompany.style.display = 'block'
    }
}