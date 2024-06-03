// TESTS
console.group('Liste des tests qui ne passent pas (si aucun = tout va bien): ');
testExo1();
testExo2();
testExo3();
testExo4();
console.groupEnd();
console.log("Fini !");

// EXO 1
function sum(...terms) {
    if (terms.length < 1) {
        throw new Error("At least one number is expected");
    }
    return terms.reduce((prev, curr) => prev + curr, 0);
}

function testExo1() {
    let message = 'No Errors';
    try {
        sum();
    } catch (e) {
        message = e.message ?? e;
    }
    const expected = 'At least one number is expected';
    console.assert(message == expected, `expected ${expected}, actual: ${message}`);
    console.assert(sum(1) == 1, `Expected 1, got ${sum(1)}`);
    console.assert(sum(1, 2, 3) == 6, `Expected 6, got ${sum(1, 2, 3)}`);
}

// EXO 2
function filter(array, predicate) {
    const newArray = [];
    for (const item of array) {
        if (predicate(item)) {
            newArray.push(item);
        }
    }
    return newArray;
}
function testExo2() {
    const array = [1, 2, 3, 4, 5];
    const filetredArray = filter(array, item => item > 2);
    const expected = String([3, 4, 5]); 
    const actual = String(filetredArray); 
    console.assert(expected === actual, `Expected ${expected}, actual: ${actual}`);
    // Nous venons d'implémenter la méthode filter de array.
    // Code équivalent en l'utilisant : const filetredArray = array.filter(item => item > 2);
}

// EXO 3
function map(array, transform) {
    const newArray = [];
    for (const item of array) {
        newArray.push(transform(item));
    }
    return newArray;
}
function testExo3() {
    const array = [1, 2, 3, 4, 5];
    const doubled = map(array, item => item * 2);
    const expected = String([2, 4, 6, 8, 10]);
    const actual = String(doubled);
    console.assert(expected === actual, `Expected ${expected}, actual: ${actual}`);
}

// EXO 4
function processDataImperative(csvText) {
    const result = [];
    const lines = csvText.split('\n');
    for (let i = 1; i < lines.length; i++){
        const csvData = lines[i].split(',');
        let website = csvData[2];
        if (website == '') {
            website = null;
        }
        result.push({
            username: csvData[0],
            realName: csvData[1],
            website: website,
            projectName: csvData[3],
        })
    }
    return result;
}
function processDataFunctionnal(csvText) {
    const result = csvText.split('\n').slice(1).map((line) => {
        const [username, realName, website, projectName] = line.split(',');
        return {
            username,
            realName,
            website: website || null,
            projectName
        };
    });
    return result;
}
function testExo4() {
    function runAssertions(csvText) {
        const result2 = processDataFunctionnal(csvText);
        const result1 = processDataImperative(csvText);
        const expected = JSON.stringify(result1);
        const actual = JSON.stringify(result2);
        console.assert(expected === actual, `Expected ${expected}, actual: ${actual}`);
    }

    fetch('https://thomas-veillard.fr/wp-content/uploads/2021/07/apache-contributors-projects.csv')
        .then(res => res.text())
        .then(runAssertions)
        .catch(console.error)
}