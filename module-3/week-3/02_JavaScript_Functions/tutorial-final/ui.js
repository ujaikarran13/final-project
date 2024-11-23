document.addEventListener('DOMContentLoaded', () => {
  if (typeof turnOn !== 'undefined' && turnOn()) {
    document.querySelector('div.container').classList.remove('d-none');

    if (typeof returnsName !== 'undefined') {
      document.getElementById('name').textContent = returnsName();
    }

    if (typeof returnGivenParameter !== 'undefined') {
      document.getElementById('given-thing').textContent = returnGivenParameter('the given thing');
    }
    if (typeof takeOptionalParameter !== 'undefined') {
      document.getElementById('default-thing').textContent = takeOptionalParameter();
    }

    let givenArray = [1, 3, 4, 12, 6, 75, 8, 15, 56];

    document.getElementById('number-given-array').textContent = JSON.stringify(givenArray);
    document.getElementById('single-digit-array').textContent = JSON.stringify(
      filterArrayToOnlySingleDigitNumbers(givenArray)
    );
    document.getElementById('doubled-array').textContent = JSON.stringify(
      mapArrayToDoubleAllNumbers(givenArray)
    );
    document.getElementById('array-product').textContent = JSON.stringify(
      reduceArrayToFindProduct(givenArray)
    );

    givenArray = ['Jackson', 'Johnson', 'Miller', 'Erickson', 'Fredrick'];

    document.getElementById('name-given-array').textContent = JSON.stringify(givenArray);
    document.getElementById('name-son-array').textContent = JSON.stringify(
      filterStringArrayForSon(givenArray)
    );
    document.getElementById('capitalized-array').textContent = JSON.stringify(
      makeNamesAllCaps(givenArray)
    );
  }
});
