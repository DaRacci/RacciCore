#!/usr/bin/env bash

PREVIOUS=$1
VERSION=$2
TEST=$3

if [ "$TEST" == "true" ]; then
  echo "TESTING"
  echo "PREVIOUS: $PREVIOUS"
  echo "VERSION: $VERSION"
  exit 0
fi

sed -i "s/version=.*/version=${VERSION}/" ./gradle.properties

# Add the modified properties file to the version change commit
git add ./gradle.properties
git commit --amend -C HEAD

git push
git push origin v"${VERSION}" # Push the new version tag

./gradlew clean build

URL="https://github.com/DaRacci/Minix/compare/v${PREVIOUS}..v${VERSION}"
grep -Poz "(?s)(?<=## \\[v${VERSION}]\\(${URL}) - ....-..-..\n).*?(?=- - -)" CHANGELOG.md >> ./.templog.md

SEMIPATH=build/libs/Minix
gh release create "v${VERSION}" -F ./.templog.md -t "Minix release ${VERSION}" $SEMIPATH-$VERSION.jar Minix-API/$SEMIPATH-API-$VERSION-sources.jar
rm ./.templog.md

gh workflow run "docs.yml" # Generate the documentation

./gradlew :Minix-API:publish # Publish from the API module

git fetch --tags origin # Fetch the tags from the origin
