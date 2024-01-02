#include <iostream>
#include <string>

using namespace std;

class ShiftCipher {
    private:
        int shiftAmount;

    public:
        // constructor
        ShiftCipher(int shiftAmount) {
            this -> shiftAmount = shiftAmount;  // initialize the shift amount
        }

        // encode the input
        string encode(const string& input) {
            char ch;
            string result = "";
            for (int i = 0; i < input.length(); i++) {
                ch = input.at(i);
                if (isalpha(ch)) {  // check if the character includes alphabet
                    char state = islower(ch) ? 'x' : 'X'; // determine the state of initial character in terms of lowercase or uppercase
                    /* add the shift amount to the position
                    then take modulo 26 to handle wrapping around the alphabet and append to the result*/
                    result += static_cast<char>((ch - state + shiftAmount) % 26 + state);
                }
                else {
                    result += ch;  // if not an alphabet then append to the result directly
                }
            }
            return result;  // return encoded input
        }

        // decode the input to convert encoded to the original
        string decode(const string& encoded) {
            ShiftCipher decode(-shiftAmount);  // create a new object to decode
            return decode.encode(encoded);
        }
};

int main() {
    ShiftCipher shiftChiper(1);  // create a ShiftCipher object with a shift amount of 1

    // encode and decode lowercase input
    string current = "abcd";
    string encoded = shiftChiper.encode(current);
    string decoded = shiftChiper.decode(encoded);

    cout << "Original input: " << current << endl;
    cout << "Encoded version: " << encoded << endl;
    cout << "Decoded version: " << decoded << endl;
    cout<<endl;

    // encode and decode uppercase input
    string currentUpper = "ABCD";
    string encodedUpper = shiftChiper.encode(currentUpper);
    string decodedUpper = shiftChiper.decode(encodedUpper);

    cout << "Uppercase input: " << currentUpper << endl;
    cout << "Encoded version: " << encodedUpper << endl;
    cout << "Decoded version: " << decodedUpper << endl;
    cout<<endl;

    // encode and decode lowercase & uppercase input 
    string current_ = "Sude";
    string encoded_ = shiftChiper.encode(current_);
    string decoded_ = shiftChiper.decode(encoded_);

    cout << "Original input: " << current_ << endl;
    cout << "Encoded version: " << encoded_ << endl;
    cout << "Decoded version: " << decoded_ << endl;

    return 0;
}
