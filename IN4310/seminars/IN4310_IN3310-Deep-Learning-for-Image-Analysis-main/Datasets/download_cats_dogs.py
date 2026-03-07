import torchvision
import os

def download_cats_dogs():
    """
    Downloads the Oxford-IIIT Pet dataset to 'Datasets/OxfordPets'.
    We will filter this for Cats and Dogs in the notebook.
    """
    base_dir = os.path.dirname(os.path.abspath(__file__))
    
    print(f"🐶🐱 Downloading Oxford-IIIT Pet Dataset to: {base_dir}")
    os.makedirs(base_dir, exist_ok=True)

    # Note: This requires 'scipy' to be installed in the environment
    try:
        print("Downloading Dataset (this may take a minute)...")
        torchvision.datasets.OxfordIIITPet(
            root=base_dir, 
            split='trainval', 
            target_types='category', 
            download=True
        )
        print("Downloading Test Split...")
        torchvision.datasets.OxfordIIITPet(
            root=base_dir, 
            split='test', 
            target_types='category', 
            download=True
        )
        print(f"✅ Done! Data is located at: {os.path.join(base_dir, 'oxford-iiit-pet')}")
        
    except ImportError:
        print("❌ Error: Missing library 'scipy'. Please run 'pip install scipy' in your terminal.")
    except Exception as e:
        print(f"❌ Error downloading: {e}")

if __name__ == "__main__":
    download_cats_dogs()