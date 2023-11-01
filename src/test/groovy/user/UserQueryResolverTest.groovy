package user

import com.ldbmcs.dgs.core.domain.user.repository.UserMapper
import com.ldbmcs.dgs.ui.graphql.resolver.query.user.UserQueryResolver
import spock.lang.Specification

class UserQueryResolverTest extends Specification {
    def userMapper = Mock(UserMapper)
    def resolver = new UserQueryResolver(userMapper: userMapper)

    def "Users"() {
        given:
        def expected = Mock(List)

        when:
        def result = resolver.users()

        then:
        1 * userMapper.selectList(null) >> expected
        result == expected
    }
}
