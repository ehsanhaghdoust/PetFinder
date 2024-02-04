package ehsan.haghdoust.petfinder.model.response

import ehsan.haghdoust.petfinder.R
import ehsan.haghdoust.petfinder.model.general.NetworkError

object ErrorResponse {
    val error_401 = NetworkError(401, R.string.error_401)
    val error_403 = NetworkError(403, R.string.error_403)
    val error_404 = NetworkError(404, R.string.error_404)
    val error_500 = NetworkError(500, R.string.error_500)
    val error_00001 = NetworkError(1, R.string.error_00001)
    val error_00002 = NetworkError(2, R.string.error_00002)
}