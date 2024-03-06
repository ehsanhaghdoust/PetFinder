package ehsan.haghdoust.petfinder.core.interceptor

interface BaseUseCase<in Parameter, out Result> {
    suspend operator fun invoke(params: Parameter): Result
}