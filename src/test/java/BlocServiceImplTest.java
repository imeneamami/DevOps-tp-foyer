import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.tpfoyer.entity.Bloc;
import tn.esprit.tpfoyer.entity.Chambre;
import tn.esprit.tpfoyer.repository.BlocRepository;
import tn.esprit.tpfoyer.service.BlocServiceImpl;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BlocServiceImplTest {
    @Mock
    private BlocRepository blocRepository;

    @InjectMocks
    private BlocServiceImpl blocService;

    @Test
    public void testRetrieveAllBlocs() {
        // Arrange
        Bloc bloc1 = new Bloc();
        Bloc bloc2 = new Bloc();

        List<Bloc> blocs = Arrays.asList(bloc1 , bloc2);
        when(blocRepository.findAll()).thenReturn(blocs);

        // Act
        List<Bloc> result = blocService.retrieveAllBlocs();

        // Assert
        assertEquals(2, result.size());
        verify(blocRepository, times(1)).findAll();
    }

    @Test
    public void testAddBloc() {
        // Arrange
        Bloc bloc = new Bloc();
        when(blocRepository.save(bloc)).thenReturn(bloc);

        // Act
        Bloc result =blocService.addBloc(bloc);

        // Assert
        assertNotNull(result);
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    public void testModifyBloc() {
        // Arrange
        Bloc bloc = new Bloc();

        when(blocRepository.save(bloc)).thenReturn(bloc);

        // Act
        Bloc result = blocService.modifyBloc(bloc);

        // Assert
        assertNotNull(result);
        verify(blocRepository, times(1)).save(bloc);
    }

    @Test
    public void testRemoveBloc() {
        // Arrange
        Long blocId = 1L;

        // Act
        blocService.removeBloc(blocId);

        // Assert
        verify(blocRepository, times(1)).deleteById(blocId);
    }
    @Test
    public void testTrouverBlocsSansFoyer() {
        // Arrange
        Bloc bloc1 = new Bloc();
        Bloc bloc2 = new Bloc();

        List<Bloc> blocsSansFoyer = Arrays.asList(bloc1, bloc2);
        when(blocRepository.findAllByFoyerIsNull()).thenReturn(blocsSansFoyer);

        // Act
        List<Bloc> result = blocService.trouverBlocsSansFoyer();

        // Assert
        assertEquals(2, result.size());
        verify(blocRepository, times(1)).findAllByFoyerIsNull();
    }

    @Test
    public void testTrouverBlocsParNomEtCap() {
        // Arrange
        Bloc bloc = new Bloc();
        String nom = "Bloc A";
        long capacite = 100;

        List<Bloc> blocs = List.of(bloc);
        when(blocRepository.findAllByNomBlocAndCapaciteBloc(nom, capacite)).thenReturn(blocs);

        // Act
        List<Bloc> result = blocService.trouverBlocsParNomEtCap(nom, capacite);

        // Assert
        assertEquals(1, result.size());
        verify(blocRepository, times(1)).findAllByNomBlocAndCapaciteBloc(nom, capacite);
    }

}