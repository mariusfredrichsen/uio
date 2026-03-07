import torchvision
import os


def download_mnist():
    """
    Downloads the MNIST dataset to the 'Datasets/MNIST' directory 
    in the project root.
    """
    base_dir = os.path.dirname(os.path.abspath(__file__))
    
    print(f"Downloading data to: {base_dir}")
    os.makedirs(base_dir, exist_ok=True)

    print("Downloading Training set...")
    torchvision.datasets.MNIST(
        root=base_dir, 
        train=True, 
        download=True
    )
    
    print("Downloading Test set...")
    torchvision.datasets.MNIST(
        root=base_dir, 
        train=False, 
        download=True
    )

    print(f"✅ Done! Data is located at: {os.path.join(base_dir, 'MNIST')}")


if __name__ == "__main__":
    download_mnist()