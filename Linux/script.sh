mkdir -p usr/data
cd usr/data
touch sample1.txt sample2.txt sample3
rm *.txt
mkdir nontextfiles
find . -type f -exec mv {} nontextfiles/ \;
