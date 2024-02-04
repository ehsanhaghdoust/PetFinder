package ehsan.haghdoust.petfinder.util

enum class NetworkCallState(s: String) {
    LOADING("LOADING"),
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    NoInternet("NoInternet")
}