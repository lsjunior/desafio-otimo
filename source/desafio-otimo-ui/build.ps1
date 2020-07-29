ng build
rm -Recurse ..\..\docker\images\nginx\html\*.html
rm -Recurse ..\..\docker\images\nginx\html\*.ico
rm -Recurse ..\..\docker\images\nginx\html\*.js
rm -Recurse ..\..\docker\images\nginx\html\*.js.map
cp dist\desafio-otimo-ui\* ..\..\docker\images\nginx\html
