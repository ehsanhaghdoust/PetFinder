package ehsan.haghdoust.petfinder.core.domain.animal

data class Links(val self: Link, val type: Link, val organization: Link)


data class Link(val href: String)
