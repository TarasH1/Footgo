package ua.lviv.footgo.entity;

import ua.lviv.footgo.repository.CaptainRepository;
import ua.lviv.footgo.repository.TeamRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class TeamUnitTests {

	private static final String CAPTAIN_NAME = "Cap";
	private static final String CAPTAIN_PHONE = "Cap@gmail.com";
	private static final String CAPTAIN_EMAIL = "30666666666";

	private static final String TEAM_NAME = "ToniTeam";



	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private TeamRepository teamRepository;

	@Autowired
	private CaptainRepository captainRepository;

	@Test
	public void checkIsSomePropertiesInDataBase() {
		// given
		Team team = new Team();
		team.setTeamName("ToniTeam");
		entityManager.persist(team);
		entityManager.flush();

		// when
		List<Team> teamFromDataStore = teamRepository.findByTeamName(team.getTeamName());

		// then
		assertThat(teamFromDataStore.get(0).getTeamName())
				.isEqualTo(team.getTeamName());

		
	}

	@Test
	public void checkTeamCaptainRelation() {
		// given
		Team team = new Team();
		team.setTeamName(TEAM_NAME);

		Captain captain = new Captain();
		captain.setCaptainName(CAPTAIN_NAME);
		captain.setCaptainEmail(CAPTAIN_EMAIL);
		captain.setCaptainPhone(CAPTAIN_PHONE);

		captain.setTeam(team);
		team.setCaptain(captain);

		entityManager.persist(team);
		entityManager.persist(captain);
		entityManager.flush();

		// when
		List<Team> teamFromDataStore = teamRepository.findByTeamName(team.getTeamName());

		// then
		assertThat(teamFromDataStore.get(0).getTeamName())
				.isEqualTo(team.getTeamName());

		assertThat(teamFromDataStore.get(0).getCaptain().getCaptainEmail()).isEqualTo(team.getCaptain().getCaptainEmail());
		assertThat(teamFromDataStore.get(0).getCaptain().getCaptainPhone()).isEqualTo(team.getCaptain().getCaptainPhone());
		assertThat(teamFromDataStore.get(0).getCaptain().getCaptainName()).isEqualTo(team.getCaptain().getCaptainName());
	}

	@After
	public void cleanUp() {
		teamRepository.deleteAll();
	}
	
}
